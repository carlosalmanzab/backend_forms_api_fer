package com.form_builder.backend_forms_fer.forms.repository;

import com.form_builder.backend_forms_fer.forms.model.formConnectionAuthorization.FormConnectionAuthoriczationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormConnectionAuthorizationRepository extends
        JpaRepository<FormConnectionAuthoriczationJpaEntity, Long>,
        IFormRepository<FormConnectionAuthoriczationJpaEntity, Long> {
}