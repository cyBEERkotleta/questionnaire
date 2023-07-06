package com.app.questionnaire.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object для сущности "форма (анкета)"
 *
 * @author Катя Левкович
 * @version 1.1, 27.06.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormDTO {
    private Long id;
    private String name;
    private boolean shown;
    private List<FieldDTO> fields;
    private List<AnsweredFormDTO> answeredForms;
}