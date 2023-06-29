package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * конкретный сервис для управления операциями с пользователями
 *
 * @author Катя Левкович
 * @version 1.0, 25.06.2023
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
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User saveUserAndCreateId(User user) {
        return userRepository.save(user);
    }
}
