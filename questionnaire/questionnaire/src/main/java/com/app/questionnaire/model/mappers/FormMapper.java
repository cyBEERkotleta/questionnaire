package com.app.questionnaire.model.mappers;

import com.app.questionnaire.model.dto.FormDTO;
import com.app.questionnaire.model.entity.Form;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * преобразователь Form-сущности в Form-DTO
 *
 * @author Катя Левкович
 * @version 1.0, 27.06.2023
 */
@Mapper
public interface FormMapper {
    public FormMapper INSTANCE = Mappers.getMapper(FormMapper.class);

    public FormDTO toDTO(Form form);
}