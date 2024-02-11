package com.example.thinker.repository;

import com.example.thinker.domain.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    Choice findByChoiceForm_IdAndParticipant_Id(Long choiceFormId, Long participantId);

    List<Choice> findAllByChoiceForm_Id(Long choiceFormId);
}
