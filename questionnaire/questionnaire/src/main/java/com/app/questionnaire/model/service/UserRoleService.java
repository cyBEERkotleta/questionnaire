package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.UserRole;
import com.app.questionnaire.model.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * конкретный сервис для управления операциями
 * со списком полов пользователей
 *
 * @author Катя Левкович
 * @version 1.1, 07.07.2023
 */
@Service
@RequiredArgsConstructor
public class UserRoleService implements IUserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole getRoleById(Short id) {
        return userRoleRepository.getUserRoleById(id);
    }
}
