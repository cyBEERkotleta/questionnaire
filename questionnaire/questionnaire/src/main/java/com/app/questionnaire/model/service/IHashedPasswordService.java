package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.HashedPassword;

/**
 * абстрактный сервис для управления паролями пользователей
 *
 * @author Катя Левкович
 * @version 1.2, 06.07.2023
 */
public interface IHashedPasswordService {
    public HashedPassword savePassword(HashedPassword hashedPassword);
    /*public HashedPassword changePassword(String oldPassword, String newPassword);*/

    public String encryptPassword(String password);

    public boolean checkPassword(String password, String hashedPassword);
}
