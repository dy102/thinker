package com.example.thinker.repository;

import com.example.thinker.domain.ReplyLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyLikeRepository extends JpaRepository<ReplyLike, Long> {
    ReplyLike findByReply_IdAndMember_Id(Long replyId, Long memberId);
}
