package com.app.questionnaire.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object для сущности "ответ пользователя"
 *
 * @author Катя Левкович
 * @version 1.0, 27.06.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDTO {
    private Long id;
    private String text;
    private FieldDTO field;
}
