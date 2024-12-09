package com.form_builder.backend_forms_fer.forms.repository;

import com.form_builder.backend_forms_fer.forms.model.FormThereAreNoRoadLinesPermits.FormThereAreNoRoadLinesPermitsJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormThereAreNoRoadLinesPermitsRepository
        extends
        JpaRepository<FormThereAreNoRoadLinesPermitsJpaEntity, Long>,
        IFormRepository<FormThereAreNoRoadLinesPermitsJpaEntity, Long>{
}
