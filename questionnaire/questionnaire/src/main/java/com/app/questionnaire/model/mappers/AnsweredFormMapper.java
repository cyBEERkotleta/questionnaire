package com.app.questionnaire.model.mappers;

import com.app.questionnaire.model.dto.AnsweredFormDTO;
import com.app.questionnaire.model.entity.AnsweredForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * преобразователь AnsweredForm-сущности в AnsweredForm-DTO
 *
 * @author Катя Левкович
 * @version 1.1, 27.06.2023
 */
@Mapper
public interface AnsweredFormMapper {
    public AnsweredFormMapper INSTANCE = Mappers.getMapper(AnsweredFormMapper.class);

    public AnsweredFormDTO toDTO(AnsweredForm answeredForm);
    public List<AnsweredFormDTO> toDTOs(List<AnsweredForm> answeredForms);
    public AnsweredForm fromDTO(AnsweredFormDTO answeredFormDTO);
}