package com.form_builder.backend_forms_fer.forms.model.formAuthorizationProcedures;

import com.form_builder.backend_forms_fer.forms.model.shared.BaseFormJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "form_authorization_procedures")
public class FormAuthorizationProceduresJpaEntity extends BaseFormJpaEntity {
    private String fromAddress;
    private String date;
    private String fromName;
    private String fromIdentification;
    private String fromLocation;
    private String toName;
    private String toIdentification;
    private String projectName;
    private String projectLocation;
    private String projectAddress;
    private String fromPhone;
    private String fromEmail;
}
