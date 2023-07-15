package com.app.questionnaire.controller;

import com.app.questionnaire.additional.RequestResult;
import com.app.questionnaire.additional.UserWithPassword;
import com.app.questionnaire.exception.AccessDeniedException;
import com.app.questionnaire.exception.MailException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.mail.MailService;
import com.app.questionnaire.model.dto.UserDTO;
import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.mappers.UserMapper;
import com.app.questionnaire.model.service.IUserService;
import com.app.questionnaire.security.TokenHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * контроллер для обработки
 * запросов, связанных с почтой
 *
 * @author Катя Левкович
 * @version 1.0, 14.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;
    private final IUserService userService;
    private final TokenHandler tokenHandler;

    @PostMapping("/get")

    @PostMapping("/send_confirmation_email")
    public RequestResult sendConfirmationEmail(@RequestBody UserWithPassword userWithPassword) throws UserException {
        UserDTO userDTO = userWithPassword.getUser();
        String password = userWithPassword.getPassword();

        System.out.println(userDTO.toString());
        User user = UserMapper.INSTANCE.fromDTO(userDTO);

        userService.checkUserIsLegalForRegistration(user, password);
        mailService.sendRegistrationLink(user.getEmail(), user, password);

        return new RequestResult(true, "Письмо для подтверждения регистрации послано");
    }

    @PostMapping("/send_notification_password_changed")
    public RequestResult sendNotificationPasswordChanged(@RequestBody String email) {
        mailService.sendNotificationAboutPasswordChange(email);

        return new RequestResult(true, "Письмо, оповещающее о смене пароля, послано");
    }

    @PostMapping("/send_password_restoration")
    public RequestResult sendPasswordRestoration(@RequestBody String token) throws AccessDeniedException {
        String email = tokenHandler.getHashedLoginDataFromToken(token).getEmail();

        mailService.sendPasswordRestoration(email, token);

        return new RequestResult(true, "Письмо для восстановления пароля послано");
    }

    @ExceptionHandler(UserException.class)
    public RequestResult handleException(UserException exception) {
        return new RequestResult(false, exception.getMessage());
    }

    @ExceptionHandler(MailException.class)
    public RequestResult handleException(AccessDeniedException exception) {
        return new RequestResult(false, "Недостаточно прав для этого действия");
    }
}
