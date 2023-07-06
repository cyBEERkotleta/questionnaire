package com.app.questionnaire.model.mappers;

import com.app.questionnaire.model.dto.FieldTypeDTO;
import com.app.questionnaire.model.entity.FieldType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * преобразователь FieldType-сущности в FieldType-DTO
 *
 * @author Катя Левкович
 * @version 1.1, 27.06.2023
 */
@Mapper
public interface FieldTypeMapper {
    public FieldTypeMapper INSTANCE = Mappers.getMapper(FieldTypeMapper.class);

    public FieldTypeDTO toDTO(FieldType fieldType);
    public List<FieldTypeDTO> toDTOs(List<FieldType> fieldType);
    public FieldType fromDTO(FieldTypeDTO fieldTypeDTO);
}