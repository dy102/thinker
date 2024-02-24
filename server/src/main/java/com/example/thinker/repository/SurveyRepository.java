package com.example.thinker.repository;

import com.example.thinker.domain.Member;
import com.example.thinker.domain.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Page<Survey> findByIsPremiumIsTrueOrderByIdDesc(PageRequest pageRequest);

    Page<Survey> findAllByIdLessThanOrderByIdDesc(Long lastId, PageRequest pageRequest);

    @Query("SELECT s FROM Survey s ORDER BY s.participants LIMIT 100")
    List<Survey> find100ByPopular();

    Page<Survey> findAllByWriterOrderByIdDesc(Member member, PageRequest pageRequest);

    Page<Survey> findAllByWriterOrderByParticipantsDesc(Member member, PageRequest pageRequest);

    Page<Survey> findAllByTitleContainingIgnoreCaseOrderByIdDesc(String title, Long lastId, PageRequest pageRequest);

    @Query("SELECT s FROM Survey s WHERE LOWER(s.title) LIKE '%your_search_term%' ORDER BY s.participants desc LIMIT 100")
    List<Survey> search100ByTitleAndPopular();

    Page<Survey> findAllByWriter_NameContainingIgnoreCaseOrderByIdDesc(String name, Long lastId, PageRequest pageRequest);

    @Query("SELECT s FROM Survey s WHERE LOWER(s.writer.name) LIKE '%your_search_term%' ORDER BY s.participants desc LIMIT 100")
    List<Survey> search100ByNameAndPopular();
}
