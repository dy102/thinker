package com.example.thinker.repository;

import com.example.thinker.domain.Member;
import com.example.thinker.domain.Subjective;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectiveRepository extends JpaRepository<Subjective, Long> {
    Subjective findBySubjectiveForm_IdAndParticipant_Id(Long subjectiveFormId, Long participantId);

    List<Subjective> findAllBySubjectiveForm_Id(Long subjectiveFormId);

    @Query("select s from Subjective s where s.participant=:member order by s.subjectiveForm.survey.id desc")
    Page<Subjective> findAllByParticipantByRecent(Member member, PageRequest pageRequest);

    @Query("select s from Subjective s where s.participant=:member order by s.subjectiveForm.survey.participants desc")
    Page<Subjective> findAllByParticipantByPopular(Member member, PageRequest pageRequest);
}
