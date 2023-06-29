package com.app.questionnaire.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object для сущности "тема"
 *
 * @author Катя Левкович
 * @version 1.0, 27.06.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicDTO {
    private Long id;
    private String name;
    private String description;
    private List<FormDTO> forms;
}
