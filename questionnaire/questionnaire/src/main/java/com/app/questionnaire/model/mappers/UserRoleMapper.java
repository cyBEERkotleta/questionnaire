package com.app.questionnaire.model.mappers;

import com.app.questionnaire.model.dto.UserRoleDTO;
import com.app.questionnaire.model.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * преобразователь UserRole-сущности в UserRole-DTO
 *
 * @author Катя Левкович
 * @version 1.0, 27.06.2023
 */
@Mapper
public interface UserRoleMapper {
    public UserRoleMapper INSTANCE = Mappers.getMapper(UserRoleMapper.class);

    public UserRoleDTO toDTO(UserRole userRole);
}