package com.app.questionnaire.model.service;

import com.app.questionnaire.exception.HashedPasswordException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.entity.HashedPassword;
import com.app.questionnaire.model.entity.User;
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
public class HashedPasswordService implements IHashedPasswordService {
    private final HashedPasswordRepository hashedPasswordRepository;

    @Override
    public HashedPassword savePassword(HashedPassword hashedPassword) {
        return hashedPasswordRepository.save(hashedPassword);
    }

    @Override
    public HashedPassword changePassword(User user, String oldPassword, String newPassword)
            throws HashedPasswordException {
        boolean passwordsMatch = check(oldPassword, user.getHashedPassword().getHash());
        if (!passwordsMatch)
            throw new HashedPasswordException("Неверно введён предыдущий пароль");

        boolean samePasswordAsWas = oldPassword.equals(newPassword);
        if (samePasswordAsWas)
            throw new HashedPasswordException("Новый пароль такой же, какой был до этого");

        String hash = encrypt(newPassword);
        HashedPassword hashedPassword = user.getHashedPassword();
        hashedPassword.setHash(hash);

        savePassword(hashedPassword);

        return hashedPassword;
    }

    @Override
    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean check(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
