package com.app.questionnaire.model.service;

import com.app.questionnaire.exception.AccessDeniedException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.entity.User;

import java.util.List;

/**
 * абстрактный сервис для управления операциями с пользователями
 * для взаимозаменяемости различных реализаций сервисов
 *
 * @author Катя Левкович
 * @version 1.2, 25.06.2023
 */
public interface IUserService {
    public List<User> findAll();
    public User getUserById(Long id);
    public User getUserByEmail(String email);
    public void deleteUserById(Long id);
    public User saveUser(User user);
    public User registerUser(User user, String password) throws UserException;
    public User registerUser(User user) throws UserException;
    public void checkUserIsLegalForRegistration(User user, String password) throws UserException;
    public User loginUser(String email, String password) throws UserException;
    public User getUserByToken(String token) throws UserException, AccessDeniedException;
    public String createTokenFromUser(User user);
    public User changePassword(User user, String oldPassword, String newPassword) throws UserException;
    public User changePassword(User user, String newPassword) throws UserException;
}
