package com.example.thinker.repository;

import com.example.thinker.domain.SubjectiveForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectiveFormRepository extends JpaRepository<SubjectiveForm, Long> {

}
