package com.app.questionnaire.controller;

import com.app.questionnaire.model.dto.UserDTO;
import com.app.questionnaire.model.dto.UserRoleDTO;
import com.app.questionnaire.model.entity.UserRole;
import com.app.questionnaire.model.mappers.UserRoleMapper;
import com.app.questionnaire.model.service.IUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с различными ролями пользователей
 *
 * @author Катя Левкович
 * @version 1.1, 07.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserRoleController {
    private final IUserRoleService userRoleService;

    @GetMapping("/user_roles")
    public List<UserRoleDTO> getUserRoles() {
        List<UserRole> userRoles = userRoleService.findAll();
        return UserRoleMapper.INSTANCE.toDTOs(userRoles);
    }

    @GetMapping("/user_roles/{id}")
    public UserRoleDTO getUserRoleById(@PathVariable Short id) {
        UserRole userRole = userRoleService.getRoleById(id);
        return UserRoleMapper.INSTANCE.toDTO(userRole);
    }
}