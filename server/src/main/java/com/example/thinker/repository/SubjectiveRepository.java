package com.example.thinker.repository;

import com.example.thinker.domain.Subjective;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectiveRepository extends JpaRepository<Subjective, Long> {
    Subjective findBySubjectiveForm_IdAndParticipant_Id(Long subjectiveId, Long participantId);
}
