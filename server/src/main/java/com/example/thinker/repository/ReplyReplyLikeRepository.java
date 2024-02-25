package com.example.thinker.repository;

import com.example.thinker.domain.ReplyReplyLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyReplyLikeRepository extends JpaRepository<ReplyReplyLike, Long> {
    ReplyReplyLike findByReplyReply_IdAndMember_Id(Long replyReplyId, Long memberId);
}
