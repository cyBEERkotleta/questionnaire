package com.app.questionnaire.model.mappers;

import com.app.questionnaire.model.dto.AnsweredFormDTO;
import com.app.questionnaire.model.entity.AnsweredForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * преобразователь AnsweredForm-сущности в AnsweredForm-DTO
 *
 * @author Катя Левкович
 * @version 1.0, 27.06.2023
 */
@Mapper
public interface AnsweredFormMapper {
    public AnsweredFormMapper INSTANCE = Mappers.getMapper(AnsweredFormMapper.class);

    public AnsweredFormDTO toDTO(AnsweredForm answeredForm);
}