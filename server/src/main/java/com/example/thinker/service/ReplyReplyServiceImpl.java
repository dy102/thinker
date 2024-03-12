package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.domain.Point;
import com.example.thinker.domain.Reply;
import com.example.thinker.domain.ReplyReply;
import com.example.thinker.domain.ReplyReplyLike;
import com.example.thinker.domain.Thinking;
import com.example.thinker.dto.ReplyDtoBase;
import com.example.thinker.dto.ReplyReplyDto;
import com.example.thinker.dto.TotalReplyDtos;
import com.example.thinker.dto.request.ContentsRequest;
import com.example.thinker.dto.response.RepliesResponse;
import com.example.thinker.repository.MemberRepository;
import com.example.thinker.repository.PointRepository;
import com.example.thinker.repository.ReplyReplyLikeRepository;
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

import static com.example.thinker.constants.ErrorConst.NO_PERMISSION;
import static com.example.thinker.constants.ErrorConst.NO_POST;
import static com.example.thinker.constants.ErrorConst.NO_REPLY;
import static com.example.thinker.constants.ErrorConst.NO_REPLY_REPLY;
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
public class ReplyReplyServiceImpl implements ReplyReplyService {
    private final ReplyRepository replyRepository;
    private final ReplyReplyRepository replyReplyRepository;
    private final ThinkingRepository thinkingRepository;
    private final ReplyReplyLikeRepository replyReplyLikeRepository;
    private final PointRepository pointRepository;
    private final MemberRepository memberRepository;

    private final MemberService memberService;


    @Override
    public RepliesResponse getReplyReplies(Member loginMember, Long thinkingId, Long replyId) {
        Optional<Thinking> thinking = thinkingRepository.findById(thinkingId);
        if (thinking.isPresent()) {
            Optional<Reply> reply = replyRepository.findById(replyId);
            if (reply.isPresent()) {
                List<ReplyReply> replyReplies = replyReplyRepository.findAllByReply_Id(replyId);
                List<ReplyDtoBase> replyReplyDtos = new ArrayList<>();
                for (ReplyReply replyReply : replyReplies) {
                    makeReplyReplyDto(loginMember, replyReply, thinking.get(), reply.get(), replyReplyDtos);
                }
                TotalReplyDtos totalReplyDtos = new TotalReplyDtos(replyReplyDtos);
                boolean isManager = false;
                if (loginMember != null && loginMember.getGrade().equals(MANAGER.getName())) {
                    isManager = true;
                }
                return new RepliesResponse(isManager, totalReplyDtos);
            }
            throw new IllegalArgumentException(NO_REPLY);
        }
        throw new IllegalArgumentException(NO_POST);
    }

    private void makeReplyReplyDto(Member loginMember, ReplyReply replyReply, Thinking thinking, Reply reply, List<ReplyDtoBase> replyReplyDtos) {
        boolean isLiked = false;
        String who = NOT_AUTHOR_AND_NOT_ME;

        if (loginMember != null
                && replyReplyLikeRepository
                .findByReplyReply_IdAndMember_Id(replyReply.getId(), loginMember.getId()) != null
        ) {
            isLiked = true;
        }

        if (loginMember != null) {
            if (thinking.getWriter().getId().equals(loginMember.getId())) {
                if (replyReply.getMember().getId().equals(loginMember.getId())) {
                    who = AUTHOR_AND_ME;
                } else {
                    who = AUTHOR_AND_NOT_ME;
                }
            } else {
                if (replyReply.getMember().getId().equals(loginMember.getId())) {
                    who = NOT_AUTHOR_AND_ME;
                }
            }
        }
        ReplyReplyDto replyReplyDto = new ReplyReplyDto(reply.getId(),
                replyReply.getId(), replyReply.getMember().getId(),
                replyReply.getContents(), replyReply.getLikeCount(), replyReply.getCreatedAt().toString(),
                isLiked, who);
        replyReplyDtos.add(replyReplyDto);
    }

    @Override
    public void postReplyReply(Member loginMember, ContentsRequest contentsRequest) {
        Optional<Reply> reply = replyRepository.findById(contentsRequest.contentId());
        if (reply.isPresent()) {
            ReplyReply replyReply = new ReplyReply(
                    null, reply.get(), loginMember,
                    contentsRequest.contents(), 0L, Timestamp.valueOf(LocalDateTime.now())
            );
            //포인트 추가
            if (reply.get().getThinking().getIsPremium()) {
                int sizePoint = contentsRequest.contents().length() / POINT_LENGTH;
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
                Long amount = (long) (contentsRequest.contents().length() / 10);
                Point point = new Point();
                point.setMember(loginMember);
                point.setExplanation(REPLY_TO_THINKING);
                point.setAmount(amount);
                pointRepository.save(point);

                loginMember.setPoint(loginMember.getPoint() + amount);
                loginMember.setAccumulatedPoint(loginMember.getAccumulatedPoint() + amount);
                memberRepository.save(loginMember);
            }
            memberService.setGradeByAccumulatedPoint(loginMember);
            replyReplyRepository.save(replyReply);
            return;
        }
        throw new IllegalArgumentException(NO_REPLY);
    }

    @Override
    public void updateReplyReply(Member loginMember, ContentsRequest contentsRequest) {
        Optional<ReplyReply> replyReply = replyReplyRepository.findById(contentsRequest.contentId());
        if (replyReply.isPresent()) {
            if (replyReply.get().getMember().getId().equals(loginMember.getId())) {
                replyReply.get().setContents(contentsRequest.contents());
                return;
            }
            throw new IllegalArgumentException(NO_PERMISSION);
        }
        throw new IllegalArgumentException(NO_REPLY_REPLY);
    }

    @Override
    public void deleteReplyReply(Member loginMember, Long replyReplyId) {
        Optional<ReplyReply> replyReply = replyReplyRepository.findById(replyReplyId);
        if (replyReply.isPresent()) {
            if (replyReply.get().getMember().getId().equals(loginMember.getId())) {
                replyReplyRepository.delete(replyReply.get());
                return;
            }
            throw new IllegalArgumentException(NO_PERMISSION);
        }
        throw new IllegalArgumentException(NO_REPLY_REPLY);
    }

    @Override
    public boolean likeReplyReplies(Member loginMember, Long replyReplyId) {
        Optional<ReplyReply> replyReply = replyReplyRepository.findById(replyReplyId);
        if (replyReply.isPresent()) {
            ReplyReplyLike replyReplyLike =
                    replyReplyLikeRepository.findByReplyReply_IdAndMember_Id(replyReplyId, loginMember.getId());
            if (replyReplyLike == null) {
                ReplyReplyLike newLike = new ReplyReplyLike(
                        null, replyReply.get(), loginMember
                );
                replyReplyLikeRepository.save(newLike);
                return true;
            } else {
                replyReplyLikeRepository.delete(replyReplyLike);
                return false;
            }
        }
        throw new IllegalArgumentException(NO_REPLY_REPLY);
    }

}
