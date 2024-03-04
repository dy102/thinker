package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.domain.Point;
import com.example.thinker.domain.Reply;
import com.example.thinker.domain.ReplyLike;
import com.example.thinker.domain.Thinking;
import com.example.thinker.dto.ReplyDto;
import com.example.thinker.dto.TotalReplyDtos;
import com.example.thinker.dto.response.RepliesResponse;
import com.example.thinker.repository.MemberRepository;
import com.example.thinker.repository.PointRepository;
import com.example.thinker.repository.ReplyLikeRepository;
import com.example.thinker.repository.ReplyReplyRepository;
import com.example.thinker.repository.ReplyRepository;
import com.example.thinker.repository.ThinkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.thinker.constants.ErrorConst.NO_LIKE_IN_MY_POST;
import static com.example.thinker.constants.ErrorConst.NO_PERMISSION;
import static com.example.thinker.constants.ErrorConst.NO_POST;
import static com.example.thinker.constants.ErrorConst.NO_REPLY;
import static com.example.thinker.constants.PointConst.POINT_LENGTH;
import static com.example.thinker.constants.PointConst.POINT_MAX_BOUND_IN_REPLY;
import static com.example.thinker.constants.PointConst.PREMIUM_THINKING_BONUS;
import static com.example.thinker.constants.PointConst.REPLY_TO_PREMIUM_THINKING;
import static com.example.thinker.constants.PointConst.REPLY_TO_THINKING;
import static com.example.thinker.constants.ServiceConst.AUTHOR_AND_ME;
import static com.example.thinker.constants.ServiceConst.AUTHOR_AND_NOT_ME;
import static com.example.thinker.constants.ServiceConst.NOT_AUTHOR_AND_ME;
import static com.example.thinker.constants.ServiceConst.NOT_AUTHOR_AND_NOT_ME;
import static com.example.thinker.domain.Grade.MANAGER;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final ThinkingRepository thinkingRepository;
    private final ReplyLikeRepository replyLikeRepository;
    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;
    private final ReplyReplyRepository replyReplyRepository;

    private final MemberService memberService;

    @Override
    public RepliesResponse getReplies(Member loginMember, Long thinkingId) {
        Optional<Thinking> thinking = thinkingRepository.findById(thinkingId);
        if (thinking.isPresent()) {
            List<Reply> replies = replyRepository.findAllByThinking_Id(thinkingId);
            List<ReplyDto> replyDtos = new ArrayList<>();
            for (Reply reply : replies) {
                makeReplyDto(loginMember, thinkingId, reply, thinking.get(), replyDtos);
            }
            TotalReplyDtos toTalReplyDtos = new TotalReplyDtos(replyDtos);
            boolean isManager = false;
            if (loginMember != null && loginMember.getGrade().equals(MANAGER.getName())) {
                isManager = true;
            }
            return new RepliesResponse(isManager, toTalReplyDtos);
        }
        throw new IllegalArgumentException(NO_POST);
    }

    private void makeReplyDto(Member loginMember, Long thinkingId, Reply reply, Thinking thinking, List<ReplyDto> replyDtos) {
        boolean isLiked = false;
        String who = NOT_AUTHOR_AND_NOT_ME;

        if (loginMember != null
                && replyLikeRepository
                .findByReply_IdAndMember_Id(reply.getId(), loginMember.getId()) != null
        ) {
            isLiked = true;
        }

        if (loginMember != null) {
            if (thinking.getWriter().getId().equals(loginMember.getId())) {
                if (reply.getMember().getId().equals(loginMember.getId())) {
                    who = AUTHOR_AND_ME;
                } else {
                    who = AUTHOR_AND_NOT_ME;
                }
            } else {
                if (reply.getMember().getId().equals(loginMember.getId())) {
                    who = NOT_AUTHOR_AND_ME;
                }
            }
        }
        boolean isReplied = false;
        if (!replyReplyRepository.findAllByReply_Id(reply.getId()).isEmpty()) {
            isReplied = true;
        }
        ReplyDto replyDto = new ReplyDto(thinkingId, reply.getId(), reply.getMember().getId(),
                reply.getContents(), reply.getLikeCount(), reply.getCreatedAt().toString(),
                isLiked, who, isReplied);
        replyDtos.add(replyDto);
    }

    @Override
    public void postReplies(Member loginMember, Long thinkingId, String replyContent) {
        Optional<Thinking> thinking = thinkingRepository.findById(thinkingId);
        if (thinking.isPresent()) {
            Reply reply = new Reply();
            reply.setMember(loginMember);
            reply.setThinking(thinking.get());
            reply.setContents(replyContent);
            reply.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            reply.setLikeCount(0L);
            replyRepository.save(reply);

            //포인트 추가
            if (!thinking.get().getWriter().getId().equals(loginMember.getId())) {
                if (thinking.get().getIsPremium()) {
                    int sizePoint = replyContent.length() / POINT_LENGTH;
                    if (sizePoint > POINT_MAX_BOUND_IN_REPLY) {
                        sizePoint = POINT_MAX_BOUND_IN_REPLY;
                    }
                    Long amount = sizePoint + PREMIUM_THINKING_BONUS;
                    Point point = new Point();
                    point.setMember(loginMember);
                    point.setExplanation(REPLY_TO_PREMIUM_THINKING);
                    point.setAmount(amount);
                    pointRepository.save(point);

                    loginMember.setPoint(loginMember.getPoint() + amount);
                    loginMember.setAccumulatedPoint(loginMember.getAccumulatedPoint() + amount);
                    memberRepository.save(loginMember);
                } else {
                    Long amount = (long) (replyContent.length() / POINT_LENGTH);
                    Point point = new Point();
                    point.setMember(loginMember);
                    point.setExplanation(REPLY_TO_THINKING);
                    point.setAmount(amount);
                    pointRepository.save(point);

                    loginMember.setPoint(loginMember.getPoint() + amount);
                    loginMember.setAccumulatedPoint(loginMember.getAccumulatedPoint() + amount);
                    memberRepository.save(loginMember);
                }
            }
            memberService.setGradeByAccumulatedPoint(loginMember);
            return;
        }
        throw new IllegalArgumentException(NO_POST);
    }

    @Override
    public void updateReplies(Member loginMember, Long replyId, String replyContent) {
        Optional<Reply> reply = replyRepository.findById(replyId);
        if (reply.isPresent()) {
            if (reply.get().getMember().getId().equals(loginMember.getId())) {
                reply.get().setContents(replyContent);
                replyRepository.save(reply.get());
                return;
            }
            throw new IllegalArgumentException(NO_PERMISSION);
        }
        throw new IllegalArgumentException(NO_REPLY);
    }

    @Override
    public void deleteReplies(Member loginMember, Long replyId) {
        Optional<Reply> reply = replyRepository.findById(replyId);
        if (reply.isPresent()) {
            if (reply.get().getMember().getId().equals(loginMember.getId())) {
                replyRepository.delete(reply.get());
                return;
            }
            throw new IllegalArgumentException(NO_PERMISSION);
        }
        throw new IllegalArgumentException(NO_REPLY);
    }

    @Override
    public boolean likeReplies(Member loginMember, Long replyId) {
        Optional<Reply> reply = replyRepository.findById(replyId);
        if (reply.isPresent()) {
            ReplyLike replyLike = replyLikeRepository.findByReply_IdAndMember_Id(replyId, loginMember.getId());
            if (replyLike == null) {
                if (reply.get().getMember().getId().equals(loginMember.getId())) {
                    throw new IllegalArgumentException(NO_LIKE_IN_MY_POST);
                }
                ReplyLike newLike = new ReplyLike();
                newLike.setReply(reply.get());
                newLike.setMember(loginMember);
                replyLikeRepository.save(newLike);

                reply.get().setLikeCount(reply.get().getLikeCount() + 1);
                replyRepository.save(reply.get());
                return true;
            } else {
                replyLikeRepository.delete(replyLike);

                reply.get().setLikeCount(reply.get().getLikeCount() - 1);
                replyRepository.save(reply.get());
                return false;
            }
        }
        throw new IllegalArgumentException(NO_REPLY);
    }

    @Override
    public RepliesResponse findRepliesByMember(Member loginMember, Long memberId) {
        if (loginMember.getGrade().equals(MANAGER.getName())) {
            Optional<Member> member = memberRepository.findById(memberId);
            if (member.isPresent()) {
                List<Reply> replies = replyRepository.findAllByMember_Id(member.get().getId());
                List<ReplyDto> replyDtos = new ArrayList<>();
                for (Reply reply : replies) {
                    makeReplyDto(loginMember, reply.getThinking().getId(), reply, reply.getThinking(), replyDtos);
                }
                TotalReplyDtos totalReplyDtos1 = new TotalReplyDtos(replyDtos);
                return new RepliesResponse(true, totalReplyDtos1);
            }
        }
        throw new IllegalArgumentException(NO_PERMISSION);
    }
}
