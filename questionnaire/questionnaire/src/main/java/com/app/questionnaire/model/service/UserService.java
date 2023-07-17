package com.app.questionnaire.model.service;

import com.app.questionnaire.additional.HashedLoginData;
import com.app.questionnaire.exception.AccessDeniedException;
import com.app.questionnaire.exception.HashedPasswordException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.entity.HashedPassword;
import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.entity.UserRole;
import com.app.questionnaire.model.repository.UserRepository;
import com.app.questionnaire.model.validator.UserValidator;
import com.app.questionnaire.security.TokenHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * конкретный сервис для управления операциями с пользователями
 *
 * @author Катя Левкович
 * @version 1.2, 25.06.2023
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final IHashedPasswordService hashedPasswordService;
    private final TokenHandler tokenHandler;

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
    public User saveUser(User user) throws UserException {
        UserValidator.getInstance().checkValidityOrThrown(user);

        return userRepository.save(user);
    }

    @Override
    public User registerUserWithPassword(User user, String password) throws UserException {
        user.setUserRole(UserRole.member());

        checkUserIsLegalForRegistration(user, password);

        HashedPassword hashedPassword = HashedPassword.builder()
                .hash(hashedPasswordService.encrypt(password))
                .build();

        hashedPassword = hashedPasswordService.savePassword(hashedPassword);
        user.setHashedPassword(hashedPassword);

        return userRepository.save(user);
    }

    @Override
    public User registerUserWithHashedPassword(User user, String hashedPasswordStr) throws UserException {
        user.setUserRole(UserRole.member());

        checkUserWithEmailExistenceOrThrown(user.getEmail());
        UserValidator.getInstance().checkValidityOrThrown(user);

        HashedPassword hashedPassword = HashedPassword.builder()
                .hash(hashedPasswordStr)
                .build();

        hashedPassword = hashedPasswordService.savePassword(hashedPassword);
        user.setHashedPassword(hashedPassword);

        return userRepository.save(user);
    }

    @Override
    public void checkUserIsLegalForRegistration(User user, String password) throws UserException {
        user.setUserRole(UserRole.member());

        checkUserWithEmailExistenceOrThrown(user.getEmail());
        UserValidator.getInstance().checkValidityOrThrown(user);
        UserValidator.getInstance().checkPasswordOrThrown(password);
    }

    @Override
    public User loginUser(String email, String password) throws UserException {
        User user = getUserByEmail(email);
        if (user == null)
            throw new UserException("Пользователя с таким Email не существует");

        String hashedPassword = user.getHashedPassword().getHash();
        boolean passwordIsCorrect = hashedPasswordService.check(password, hashedPassword);
        if (!passwordIsCorrect)
            throw new UserException("Неправильный пароль");

        return user;
    }

    private User loginUserWithHashedPassword(String email, String hashedPassword) throws UserException {
        User user = getUserByEmail(email);
        if (user == null)
            throw new UserException("Пользователя с таким Email не существует");

        if (!hashedPassword.equals(user.getHashedPassword().getHash()))
            throw new UserException("Неправильный пароль");

        return user;
    }

    @Override
    public String createTokenFromUser(User user) {
        HashedLoginData hashedLoginData = HashedLoginData.builder()
                .email(user.getEmail())
                .hashedPassword(user.getHashedPassword().getHash())
                .build();
        return tokenHandler.createTokenFromHashedLoginData(hashedLoginData);
    }

    private void checkUserWithEmailExistenceOrThrown(String email) throws UserException {
        User user = getUserByEmail(email);
        if (user != null)
            throw new UserException("Пользователь с Email " + email + " уже существует");
    }

    @Override
    public User getUserByToken(String token) throws UserException, AccessDeniedException {
        HashedLoginData hashedLoginData = tokenHandler.getHashedLoginDataFromToken(token);
        String email = hashedLoginData.getEmail();
        String hashedPassword = hashedLoginData.getHashedPassword();

        return loginUserWithHashedPassword(email, hashedPassword);
    }

    @Override
    public User changePassword(User user, String oldPassword, String newPassword) throws UserException {
        try {
            HashedPassword hashedPassword = hashedPasswordService.changePassword(user, oldPassword, newPassword);
            user.setHashedPassword(hashedPassword);
            return user;
        } catch (HashedPasswordException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    @Override
    public User changePassword(User user, String newPassword) throws UserException {
        try {
            HashedPassword hashedPassword = hashedPasswordService.changePassword(user, newPassword);
            user.setHashedPassword(hashedPassword);
            return user;
        } catch (HashedPasswordException ex) {
            throw new UserException(ex.getMessage());
        }
    }
}
