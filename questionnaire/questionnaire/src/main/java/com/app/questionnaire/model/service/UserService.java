package com.app.questionnaire.model.service;

import com.app.questionnaire.exception.UserException;
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

        checkUserWithEmailExistence(user.getEmail());

        UserValidator.getInstance().checkValidityOrThrown(user);
        UserValidator.getInstance().checkPasswordOrThrown(password);

        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) throws UserException {
        if ()
    }

    private void checkUserWithEmailExistence(String email) throws UserException {
        User user = getUserByEmail(email);
        if (user != null)
            throw new UserException("Пользователь с Email " + email + " уже существует");
    }
}
