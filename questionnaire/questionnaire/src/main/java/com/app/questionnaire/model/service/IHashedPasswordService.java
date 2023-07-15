package com.app.questionnaire.model.service;

import com.app.questionnaire.exception.HashedPasswordException;
import com.app.questionnaire.model.entity.HashedPassword;
import com.app.questionnaire.model.entity.User;

/**
 * абстрактный сервис для управления паролями пользователей
 *
 * @author Катя Левкович
 * @version 1.2, 06.07.2023
 */
public interface IHashedPasswordService {
    public HashedPassword savePassword(HashedPassword hashedPassword);
    public HashedPassword changePassword(User user, String oldPassword, String newPassword)
            throws HashedPasswordException;
    public HashedPassword changePassword(User user, String newPassword) throws HashedPasswordException;

    public String encrypt(String password);

    public boolean check(String password, String hashedPassword);
}
