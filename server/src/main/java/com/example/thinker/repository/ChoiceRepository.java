package com.example.thinker.repository;

import com.example.thinker.domain.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    Choice findByChoiceForm_IdAndParticipant_Id(Long choiceFormId, Long participantId);

    List<Choice> findAllByParticipant_Id(Long participantId);

    List<Choice> findAllByChoiceForm_Id(Long choiceFormId);
}
