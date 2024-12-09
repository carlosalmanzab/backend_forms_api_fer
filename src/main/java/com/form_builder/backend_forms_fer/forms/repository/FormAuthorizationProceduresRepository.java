package com.form_builder.backend_forms_fer.forms.repository;

import com.form_builder.backend_forms_fer.forms.model.formAuthorizationProcedures.FormAuthorizationProceduresJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormAuthorizationProceduresRepository extends
        JpaRepository<FormAuthorizationProceduresJpaEntity, Long>,
        IFormRepository<FormAuthorizationProceduresJpaEntity, Long> {
}
