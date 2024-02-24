package com.example.thinker.repository;

import com.example.thinker.domain.ChoiceForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceFormRepository extends JpaRepository<ChoiceForm, Long> {

}
