package com.example.thinker.repository;

import com.example.thinker.domain.ReplyReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyReplyRepository extends JpaRepository<ReplyReply, Long> {
    List<ReplyReply> findAllByReply_Id(Long replyId);


}
