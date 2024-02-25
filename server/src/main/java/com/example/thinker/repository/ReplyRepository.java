package com.example.thinker.repository;

import com.example.thinker.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByMember_Id(Long memberId);

    List<Reply> findAllByThinking_Id(Long thinkingId);
}
