package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.User;

import java.util.List;

/**
 * абстрактный сервис для управления операциями с пользователями
 * для взаимозаменяемости различных реализаций сервисов
 *
 * @author Катя Левкович
 * @version 1.0, 25.06.2023
 */
public interface IUserService {
    public List<User> findAll();
    public User getUserById(Long id);
    public void deleteUserById(Long id);
    public User saveUserAndCreateId(User user);
}
