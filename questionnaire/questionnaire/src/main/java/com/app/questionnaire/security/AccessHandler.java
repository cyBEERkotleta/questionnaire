package com.app.questionnaire.security;

import com.app.questionnaire.exception.AccessDeniedException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.entity.UserRole;
import com.app.questionnaire.model.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * сервис, управляющий доступом к ресурсам на сайте
 *
 * @author Катя Левкович
 * @version 1.0, 13.07.2023
 */
@Service
@RequiredArgsConstructor
public class AccessHandler {
    private final IUserService userService;

    public User getUserByToken(String token) throws AccessDeniedException {
        try {
            return userService.getUserByToken(token);
        } catch (UserException ex) {
            throw new AccessDeniedException();
        }
    }

    public void checkTokenIsFromAdminAccountOrThrown(String token) throws AccessDeniedException {
        if (!isTokenFromAdminAccount(token))
            throw new AccessDeniedException();
    }

    public boolean isTokenFromAdminAccount(String token) throws AccessDeniedException {
        return getUserByToken(token).getUserRole().equals(UserRole.admin());
    }

    public void checkUsersAreOneEntityOrThrown(String tokenUserFirst, User userSecond) throws AccessDeniedException {
        if (!areUsersOneEntity(tokenUserFirst, userSecond))
            throw new AccessDeniedException();
    }

    public boolean areUsersOneEntity(String tokenUserFirst, User userSecond) throws AccessDeniedException {
        User userFirst = getUserByToken(tokenUserFirst);
        return userFirst.getId().equals(userSecond.getId());
    }
}
