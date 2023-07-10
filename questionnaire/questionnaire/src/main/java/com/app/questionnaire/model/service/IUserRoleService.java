package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.UserRole;

import java.util.List;

/**
 * абстрактный сервис для управления операциями
 * со списком ролей пользователей
 *
 * @author Катя Левкович
 * @version 1.0, 07.07.2023
 */
public interface IUserRoleService {
    public List<UserRole> findAll();
}