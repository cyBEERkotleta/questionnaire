package com.app.questionnaire.model.mappers;

import com.app.questionnaire.model.dto.GenderDTO;
import com.app.questionnaire.model.entity.Gender;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * преобразователь Gender-сущности в Gender-DTO
 *
 * @author Катя Левкович
 * @version 1.1, 01.07.2023
 */
@Mapper
public interface GenderMapper {
    public GenderMapper INSTANCE = Mappers.getMapper(GenderMapper.class);

    public GenderDTO toDTO(Gender gender);
    public List<GenderDTO> toDTOs(List<Gender> genders);
    public Gender fromDTO(GenderDTO genderDTO);
}