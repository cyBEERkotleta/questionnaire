package com.app.questionnaire.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object для сущности "пол пользователя"
 *
 * @author Катя Левкович
 * @version 1.0, 01.07.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenderDTO {
    private Short id;
    private String name;
}