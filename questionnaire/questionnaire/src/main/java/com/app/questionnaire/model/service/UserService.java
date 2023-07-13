package com.app.questionnaire.model.service;

import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.entity.HashedPassword;
import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.entity.UserRole;
import com.app.questionnaire.model.repository.UserRepository;
import com.app.questionnaire.model.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * конкретный сервис для управления операциями с пользователями
 *
 * @author Катя Левкович
 * @version 1.1, 25.06.2023
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final HashedHashedPasswordService hashedPasswordService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User registerUser(User user, String password) throws UserException {
        user.setRole(UserRole.member());

        checkUserWithEmailExistenceOrThrown(user.getEmail());

        UserValidator.getInstance().checkValidityOrThrown(user);
        UserValidator.getInstance().checkPasswordOrThrown(password);

        HashedPassword hashedPassword = HashedPassword.builder()
                .hash(hashedPasswordService.encryptPassword(password))
                .build();

        hashedPassword = hashedPasswordService.savePassword(hashedPassword);
        user.setHashedPassword(hashedPassword);

        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) throws UserException {
        User user = userRepository.getUserByEmail(email);
        if (user == null)
            throw new UserException("Пользователя с таким Email не существует");

        String hashedPassword = user.getHashedPassword().getHash();
        boolean passwordIsCorrect = hashedPasswordService.checkPassword(password, hashedPassword);
        if (!passwordIsCorrect)
            throw new UserException("Неправильный пароль");

        return user;
    }

    private void checkUserWithEmailExistenceOrThrown(String email) throws UserException {
        User user = getUserByEmail(email);
        if (user != null)
            throw new UserException("Пользователь с Email " + email + " уже существует");
    }

    @Override
    public void changePassword(String email, String oldPassword, String newPassword) throws UserException {
        User user = getUserByEmail(email);
        if (user == null)
            throw new UserException("Нельзя сменить пароль: пользователя с почтой " + email + " не существует");

        boolean passwordsMatch = hashedPasswordService.checkPassword(oldPassword, user.getHashedPassword().getHash());
        if (!passwordsMatch)
            throw new UserException("Неверно введён предыдущий пароль");

        boolean samePasswordAsWas = oldPassword.equals(newPassword);
        if (samePasswordAsWas)
            throw new UserException("Новый пароль такой же, какой был до этого");

        String hash = hashedPasswordService.encryptPassword(newPassword);
        HashedPassword hashedPassword = user.getHashedPassword();
        hashedPassword.setHash(hash);

        hashedPasswordService.savePassword(hashedPassword);
    }
}
