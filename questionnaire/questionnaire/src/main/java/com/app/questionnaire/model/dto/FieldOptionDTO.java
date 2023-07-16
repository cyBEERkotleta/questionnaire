package com.app.questionnaire.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object для сущности "вариант поля"
 *
 * @author Катя Левкович
 * @version 1.0, 15.07.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldOptionDTO {
    private Long id;
    private String text;
}
