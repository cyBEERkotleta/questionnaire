package com.app.questionnaire.controller;

import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.RequestResult;
import com.app.questionnaire.model.dto.GenderDTO;
import com.app.questionnaire.model.dto.UserDTO;
import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.mappers.GenderMapper;
import com.app.questionnaire.model.mappers.UserMapper;
import com.app.questionnaire.model.service.IUserService;
import com.app.questionnaire.utils.PasswordEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с пользователями
 *
 * @author Катя Левкович
 * @version 1.0, 05.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping("/users")
    public List<UserDTO> processUsers() {
        List<User> users = userService.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = UserMapper.INSTANCE.toDTO(user);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

    @GetMapping("/users/{id}")
    public UserDTO processUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return UserMapper.INSTANCE.toDTO(user);
    }

    @PostMapping("/register")
    public RequestResult registerUser(@RequestParam String email, @RequestParam String password,
                                      @RequestParam String firstName, @RequestParam String lastName,
                                      @RequestParam String phoneNumber, @RequestParam GenderDTO gender) throws UserException {
        User user = User.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .gender(GenderMapper.INSTANCE.fromDTO(gender))
                .build();

        userService.registerUser(user, password);
        return new RequestResult(true, "Аккаунт пользователя успешно зарегистрирован");
    }

    @PostMapping("/login")
    public RequestResult loginUser(@RequestParam String email, @RequestParam String password) throws UserException {

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserException.class)
    public RequestResult handleException(UserException exception) {
        return new RequestResult(false, exception.getMessage());
    }
}