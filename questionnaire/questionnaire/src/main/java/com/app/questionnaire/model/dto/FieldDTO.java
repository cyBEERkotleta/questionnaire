package com.app.questionnaire.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private FieldTypeDTO type;
    private Boolean required;
    private Boolean active;
    private List<FieldOptionDTO> options;
    private FormDTO form;
}
