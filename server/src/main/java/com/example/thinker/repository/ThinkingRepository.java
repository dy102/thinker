package com.example.thinker.repository;

import com.example.thinker.domain.Member;
import com.example.thinker.domain.Thinking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ThinkingRepository extends JpaRepository<Thinking, Long> {
    Page<Thinking> findAllByOrderByIdDesc(Pageable pageable);

    Page<Thinking> findByIsPremiumIsTrueOrderByIdDesc(Pageable pageable);

    Page<Thinking> findAllByIdLessThanOrderByIdDesc(Long lastId, Pageable pageable);

    Page<Thinking> findAllByLikeCountIsLessThanEqualOrderByLikeCountDesc(Long likeCount, Pageable pageable);

    Page<Thinking> findAllByViewCountLessThanEqualOrderByViewCountDesc(Long viewCount, Pageable pageable);

    Long countThinkingByIdIsNotNull();

    //search title
    Page<Thinking> searchAllByTitleContainingIgnoreCaseAndIdIsLessThanEqualOrderByIdDesc(String title, Long lastId, Pageable pageable);

    Page<Thinking> searchAllByTitleContainingIgnoreCaseAndLikeCountIsLessThanEqualOrderByLikeCountDesc(String title, Long lastId, Pageable pageable);

    Page<Thinking> searchAllByTitleContainingIgnoreCaseAndViewCountIsLessThanEqualOrderByViewCountDesc(String title, Long lastId, Pageable pageable);

    //search contents
    Page<Thinking> searchAllByContentsContainingIgnoreCaseAndIdIsLessThanEqualOrderByIdDesc(String content, Long lastId, Pageable pageable);

    Page<Thinking> searchAllByContentsContainingIgnoreCaseAndLikeCountIsLessThanEqualOrderByLikeCountDesc(String content, Long lastId, Pageable pageable);

    Page<Thinking> searchAllByContentsContainingIgnoreCaseAndViewCountIsLessThanEqualOrderByViewCountDesc(String content, Long lastId, Pageable pageable);

    //search writer //writer 바꿔줘야함?
    @Query("SELECT t FROM Thinking t WHERE t.writer.name=:writer")
    Page<Thinking> searchAllByWriterContainingIgnoreCaseAndIdIsLessThanEqualOrderByIdDesc(String writer, Long lastId, Pageable pageable);

    @Query("SELECT t FROM Thinking t WHERE t.writer.name=:writer")
    Page<Thinking> searchAllByWriterContainingIgnoreCaseAndLikeCountIsLessThanEqualOrderByLikeCountDesc(String writer, Long lastId, Pageable pageable);

    @Query("SELECT t FROM Thinking t WHERE t.writer.name=:writer")
    Page<Thinking> searchAllByWriterContainingIgnoreCaseAndViewCountIsLessThanEqualOrderByViewCountDesc(String writer, Long lastId, Pageable pageable);

    Page<Thinking> findAllByWriterAndIdLessThanEqualOrderByIdDesc(Member member, Long lastId, Pageable pageable);

    //내가 쓴 글
    Page<Thinking> findAllByWriterOrderByIdDesc(Member writer, Pageable pageable);

    Page<Thinking> findAllByWriterOrderByLikeCountDesc(Member writer, Pageable pageable);

    Page<Thinking> findAllByWriterOrderByViewCountDesc(Member writer, Pageable pageable);

    //댓글단 글

}
