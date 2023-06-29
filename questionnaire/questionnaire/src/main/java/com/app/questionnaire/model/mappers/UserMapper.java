package com.app.questionnaire.model.mappers;

import com.app.questionnaire.model.dto.UserDTO;
import com.app.questionnaire.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * преобразователь User-сущности в User-DTO (Data Transfer Object)
 *
 * @author Катя Левкович
 * @version 1.0, 27.06.2023
 */
@Mapper
public interface UserMapper {
    public UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public UserDTO toDTO(User user);
}