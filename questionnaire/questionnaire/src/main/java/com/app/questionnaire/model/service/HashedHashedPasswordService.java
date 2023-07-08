package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.HashedPassword;
import com.app.questionnaire.model.repository.HashedPasswordRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * конкретный сервис для управления паролями пользователей
 *
 * @author Катя Левкович
 * @version 1.1, 29.06.2023
 */
@Service
@RequiredArgsConstructor
public class HashedHashedPasswordService implements IHashedPasswordService {
    private final HashedPasswordRepository hashedPasswordRepository;

    @Override
    public HashedPassword savePassword(HashedPassword hashedPassword) {
        return hashedPasswordRepository.save(hashedPassword);
    }

    /*@Override
    public HashedPassword changePassword(String oldPassword, String newPassword) {

    }*/

    @Override
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
