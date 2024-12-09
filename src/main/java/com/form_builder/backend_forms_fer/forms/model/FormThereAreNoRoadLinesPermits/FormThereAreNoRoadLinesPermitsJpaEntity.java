package com.form_builder.backend_forms_fer.forms.model.FormThereAreNoRoadLinesPermits;

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
@Table(name = "form_connection_authorization")
public class FormThereAreNoRoadLinesPermitsJpaEntity extends BaseFormJpaEntity {
    private String date;
    private String fromAddress;
    private String fromName;
    private String fromIdentification;
    private String fromPhone;
}
