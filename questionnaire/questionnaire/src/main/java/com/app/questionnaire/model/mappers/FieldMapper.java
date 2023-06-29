package com.app.questionnaire.model.mappers;

import com.app.questionnaire.model.dto.FieldDTO;
import com.app.questionnaire.model.entity.Field;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * преобразователь Field-сущности в Field-DTO
 *
 * @author Катя Левкович
 * @version 1.0, 27.06.2023
 */
@Mapper
public interface FieldMapper {
    public FieldMapper INSTANCE = Mappers.getMapper(FieldMapper.class);

    public FieldDTO toDTO(Field field);
}