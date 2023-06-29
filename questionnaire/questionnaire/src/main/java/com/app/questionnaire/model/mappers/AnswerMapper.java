package com.app.questionnaire.model.mappers;

import com.app.questionnaire.model.dto.AnswerDTO;
import com.app.questionnaire.model.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * преобразователь Answer-сущности в Answer-DTO
 *
 * @author Катя Левкович
 * @version 1.0, 27.06.2023
 */
@Mapper
public interface AnswerMapper {
    public AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    public AnswerDTO toDTO(Answer answer);
}