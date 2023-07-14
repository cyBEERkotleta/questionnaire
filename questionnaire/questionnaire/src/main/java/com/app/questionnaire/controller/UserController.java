package com.app.questionnaire.controller;

import com.app.questionnaire.additional.*;
import com.app.questionnaire.additional.tokenable.TokenWithChangePasswordData;
import com.app.questionnaire.additional.tokenable.TokenWithUser;
import com.app.questionnaire.exception.AccessDeniedException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.dto.UserDTO;
import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.mappers.UserMapper;
import com.app.questionnaire.model.service.IUserService;
import com.app.questionnaire.security.AccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с пользователями
 *
 * @author Катя Левкович
 * @version 1.5, 05.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final AccessHandler accessHandler;

    @PostMapping("/users")
    public List<UserDTO> getUsers(@RequestBody String token) throws AccessDeniedException {
        accessHandler.checkTokenIsFromAdminAccountOrThrown(token);

        List<User> users = userService.findAll();
        return UserMapper.INSTANCE.toDTOs(users);
    }

    @PostMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id, @RequestBody String token) throws AccessDeniedException {
        User user = userService.getUserById(id);
        if (!accessHandler.areUsersOneEntity(token, user)) {
            accessHandler.checkTokenIsFromAdminAccountOrThrown(token);
        }
        return UserMapper.INSTANCE.toDTO(user);
    }

    @PostMapping("/user_by_token")
    public UserDTO getUserByToken(@RequestBody String token) throws AccessDeniedException, UserException {
        User user = userService.getUserByToken(token);
        return UserMapper.INSTANCE.toDTO(user);
    }

    @PostMapping("/save_user")
    public AuthorizeResult saveUser(@RequestBody TokenWithUser tokenWithUser) throws AccessDeniedException {
        String token = tokenWithUser.getToken();
        User userToSave = UserMapper.INSTANCE.fromDTO(tokenWithUser.getUser());

        accessHandler.checkUsersAreOneEntityOrThrown(token, userToSave);

        User user = userService.saveUser(userToSave);
        String newToken = userService.createTokenFromUser(user);

        return new AuthorizeResult(true, "Вы успешно обновили данные аккаунта", newToken);
    }

    @PostMapping("/register")
    public AuthorizeResult registerUser(@RequestBody UserWithPassword userWithPassword) throws UserException {
        UserDTO userDTO = userWithPassword.getUser();
        String password = userWithPassword.getPassword();

        System.out.println(userDTO.toString());
        User user = UserMapper.INSTANCE.fromDTO(userDTO);

        user = userService.registerUser(user, password);
        String token = userService.createTokenFromUser(user);

        return new AuthorizeResult(true, "Вы успешно зарегистрировались", token);
    }

    @PostMapping("/login")
    public AuthorizeResult loginUser(@RequestBody LoginData loginData) throws UserException {
        User user = userService.loginUser(loginData.getEmail(), loginData.getPassword());
        String token = userService.createTokenFromUser(user);

        return new AuthorizeResult(true, "Вы успешно зашли в аккаунт", token);
    }

    @PostMapping("/change_password")
    public AuthorizeResult changePassword(@RequestBody TokenWithChangePasswordData tokenWithChangePasswordData)
            throws UserException, AccessDeniedException {
        String token = tokenWithChangePasswordData.getToken();
        User user = accessHandler.getUserByToken(token);
        accessHandler.checkUsersAreOneEntityOrThrown(token, user);

        user = userService.changePassword(user.getEmail(),
                tokenWithChangePasswordData.getOldPassword(),
                tokenWithChangePasswordData.getNewPassword());

        String newToken = userService.createTokenFromUser(user);
        return new AuthorizeResult(true, "Вы успешно сменили пароль", newToken);
    }

    @ExceptionHandler(UserException.class)
    public AuthorizeResult handleException(UserException exception) {
        return new AuthorizeResult(false, exception.getMessage(), "");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public AuthorizeResult handleException(AccessDeniedException exception) {
        return new AuthorizeResult(false, "Недостаточно прав для этого действия", "");
    }
}