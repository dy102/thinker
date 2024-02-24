package com.example.thinker.repository;

import com.example.thinker.domain.MultipleChoiceForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultipleChoiceFormRepository extends JpaRepository<MultipleChoiceForm, Long> {

}
