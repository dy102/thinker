package com.example.thinker.repository;

import com.example.thinker.domain.Member;
import com.example.thinker.domain.Thinking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThinkingRepository extends JpaRepository<Thinking, Long> {
    Page<Thinking> findAllByOrderByIdDesc(Pageable pageable);

    Page<Thinking> findByIsPremiumIsTrueOrderByIdDesc(Pageable pageable);

    Page<Thinking> findAllByIdLessThanOrderByIdDesc(Long lastId, Pageable pageable);

    Page<Thinking> findAllByLikeCountIsLessThanOrderByLikeCountDesc(Long likeCount, Pageable pageable);

    Page<Thinking> findAllByViewCountLessThanOrderByViewCountDesc(Long viewCount, Pageable pageable);

    Long countThinkingByIdIsNotNull();

    //search title
    Page<Thinking> searchAllByTitleContainingIgnoreCaseAndIdIsLessThanOrderByIdDesc(String title, Long lastId, Pageable pageable);

    Page<Thinking> searchAllByTitleContainingIgnoreCaseAndLikeCountIsLessThanOrderByLikeCountDesc(String title, Long lastId, Pageable pageable);

    Page<Thinking> searchAllByTitleContainingIgnoreCaseAndViewCountIsLessThanOrderByViewCountDesc(String title, Long lastId, Pageable pageable);

    //search contents
    Page<Thinking> searchAllByContentsContainingIgnoreCaseAndIdIsLessThanOrderByIdDesc(String content, Long lastId, Pageable pageable);

    Page<Thinking> searchAllByContentsContainingIgnoreCaseAndLikeCountIsLessThanOrderByLikeCountDesc(String content, Long lastId, Pageable pageable);

    Page<Thinking> searchAllByContentsContainingIgnoreCaseAndViewCountIsLessThanOrderByViewCountDesc(String content, Long lastId, Pageable pageable);

    //search writer //writer 바꿔줘야함?
    @Query("SELECT t FROM Thinking t WHERE t.writer.name=:name AND t.id <= :lastId")
    Page<Thinking> searchAllByNameContainingIgnoreCaseAndIdIsLessThanOrderByIdDesc(@Param("name") String name, Long lastId, Pageable pageable);

    @Query("SELECT t FROM Thinking t WHERE t.writer.name=:name AND t.id <= :lastId")
    Page<Thinking> searchAllByNameContainingIgnoreCaseAndLikeCountIsLessThanOrderByLikeCountDesc(@Param("name") String name, Long lastId, Pageable pageable);

    @Query("SELECT t FROM Thinking t WHERE t.writer.name=:name AND t.id <= :lastId")
    Page<Thinking> searchAllByNameContainingIgnoreCaseAndViewCountIsLessThanOrderByViewCountDesc(@Param("name") String name, @Param("lastId") Long lastId, Pageable pageable);

    Page<Thinking> findAllByWriterAndIdLessThanOrderByIdDesc(Member member, Long lastId, Pageable pageable);

    //내가 쓴 글
    Page<Thinking> findAllByWriterOrderByIdDesc(Member writer, Pageable pageable);

    Page<Thinking> findAllByWriterOrderByLikeCountDesc(Member writer, Pageable pageable);

    Page<Thinking> findAllByWriterOrderByViewCountDesc(Member writer, Pageable pageable);

    //댓글단 글

}
