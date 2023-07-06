package com.app.questionnaire.model.mappers;

import com.app.questionnaire.model.dto.UserRoleDTO;
import com.app.questionnaire.model.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * преобразователь UserRole-сущности в UserRole-DTO
 *
 * @author Катя Левкович
 * @version 1.1, 27.06.2023
 */
@Mapper
public interface UserRoleMapper {
    public UserRoleMapper INSTANCE = Mappers.getMapper(UserRoleMapper.class);

    public UserRoleDTO toDTO(UserRole userRole);
    public List<UserRoleDTO> toDTOs(List<UserRole> userRoles);
    public UserRole fromDTO(UserRoleDTO userRoleDTO);
}