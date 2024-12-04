package com.form_builder.backend_forms_fer.forms.model.formConnectionAuthorization;

import com.form_builder.backend_forms_fer.forms.model.shared.BaseFormJpaEntity;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "form_connection_authorization")
public class FormConnectionAuthoriczationJpaEntity extends BaseFormJpaEntity {
    private String date;
    private String fromName;
    private String fromIdentification;
    private String fromLocation;
    private String fromPhone;
    private String fromAddress;
    private String structureCode;
    private String structureLocation;
    private String toName;
    private String toIdentification;
    private String toLocation;
}
