package com.app.questionnaire.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object для сущности "поле для ввода"
 *
 * @author Катя Левкович
 * @version 1.0, 27.06.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldDTO {
    private Long id;
    private String label;
    private FieldTypeDTO fieldType;
    private boolean required;
    private boolean active;
}
