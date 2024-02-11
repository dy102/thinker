package com.example.thinker.repository;

import com.example.thinker.domain.Subjective;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectiveRepository extends JpaRepository<Subjective, Long> {
    Subjective findBySubjectiveForm_IdAndParticipant_Id(Long subjectiveFormId, Long participantId);

    List<Subjective> findAllBySubjectiveForm_Id(Long subjectiveFormId);
}
