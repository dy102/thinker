package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.domain.Reply;
import com.example.thinker.domain.ReplyLike;
import com.example.thinker.domain.Thinking;
import com.example.thinker.dto.ReplyDto;
import com.example.thinker.dto.ReplyDtos;
import com.example.thinker.dto.response.RepliesResponse;
import com.example.thinker.repository.MemberRepository;
import com.example.thinker.repository.ReplyLikeRepository;
import com.example.thinker.repository.ReplyRepository;
import com.example.thinker.repository.ThinkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final ThinkingRepository thinkingRepository;
    private final ReplyLikeRepository replyLikeRepository;
    private final MemberRepository memberRepository;

    @Override
    public RepliesResponse getReplies(Member loginMember, Long thinkingId) {
        Optional<Thinking> thinking = thinkingRepository.findById(thinkingId);
        if (thinking.isPresent()) {
            List<Reply> replies = replyRepository.findAllByThinking_Id(thinkingId);
            List<ReplyDto> replyDtos = new ArrayList<>();
            for (Reply reply : replies) {
                makeReplyDto(loginMember, thinkingId, reply, thinking.get(), replyDtos);
            }
            ReplyDtos replyDtos1 = new ReplyDtos(replyDtos);
            boolean isManager = false;
            if (loginMember != null && loginMember.getGrade().equals("MANAGER")) {
                isManager = true;
            }
            return new RepliesResponse(isManager, replyDtos1);
        }
        throw new IllegalArgumentException("존재하지 않는 게시물입니다.");
    }

    private void makeReplyDto(Member loginMember, Long thinkingId, Reply reply, Thinking thinking, List<ReplyDto> replyDtos) {
        boolean isLiked = false;
        String who = "other";

        if (loginMember != null
                && replyLikeRepository
                .findByReply_IdAndMember_Id(reply.getId(), loginMember.getId()) != null
        ) {
            isLiked = true;
        }

        if (loginMember != null) {
            if (thinking.getWriter().getId().equals(loginMember.getId())) {
                if (reply.getMember().getId().equals(loginMember.getId())) {
                    who = "me(author)";
                } else {
                    who = "author";
                }
            } else {
                if (reply.getMember().getId().equals(loginMember.getId())) {
                    who = "me";
                }
            }
        }
        ReplyDto replyDto = new ReplyDto(thinkingId, reply.getId(), reply.getMember().getId(),
                reply.getContents(), reply.getLikeCount(), reply.getCreatedAt().toString(),
                isLiked, who);
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
            return;
        }
        throw new IllegalArgumentException("존재하지 않는 게시물입니다.");
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
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
    }

    @Override
    public void deleteReplies(Member loginMember, Long replyId) {
        Optional<Reply> reply = replyRepository.findById(replyId);
        if (reply.isPresent()) {
            if (reply.get().getMember().getId().equals(loginMember.getId())) {
                replyRepository.delete(reply.get());
                return;
            }
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
    }

    @Override
    public boolean likeReplies(Member loginMember, Long replyId) {
        Optional<Reply> reply = replyRepository.findById(replyId);
        if (reply.isPresent()) {
            ReplyLike replyLike = replyLikeRepository.findByReply_IdAndMember_Id(replyId, loginMember.getId());
            if (replyLike == null) {
                if (reply.get().getMember().getId().equals(loginMember.getId())) {
                    throw new IllegalArgumentException("자신의 글에는 좋아요를 누를 수 없습니다.");
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
        throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
    }

    @Override
    public RepliesResponse findRepliesByMember(Member loginMember, Long memberId) {
        if (loginMember.getGrade().equals("MANAGER")) {
            Optional<Member> member = memberRepository.findById(memberId);
            if (member.isPresent()) {
                List<Reply> replies = replyRepository.findAllByMember_Id(member.get().getId());
                List<ReplyDto> replyDtos = new ArrayList<>();
                for (Reply reply : replies) {
                    makeReplyDto(loginMember, reply.getThinking().getId(), reply, reply.getThinking(), replyDtos);
                }
                ReplyDtos replyDtos1 = new ReplyDtos(replyDtos);
                return new RepliesResponse(true, replyDtos1);
            }
        }
        throw new IllegalArgumentException("권한이 없습니다.");
    }
}
