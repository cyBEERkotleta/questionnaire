package com.app.questionnaire.controller;

import com.app.questionnaire.additional.RequestResult;
import com.app.questionnaire.additional.UserWithPassword;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.mail.MailService;
import com.app.questionnaire.model.dto.UserDTO;
import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.mappers.UserMapper;
import com.app.questionnaire.model.service.IUserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSendException;
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

    @PostMapping("/send_confirmation_email")
    public RequestResult sendConfirmationEmail(@RequestBody UserWithPassword userWithPassword)
            throws UserException, MessagingException {
        UserDTO userDTO = userWithPassword.getUser();
        String password = userWithPassword.getPassword();

        System.out.println(userDTO.toString());
        User user = UserMapper.INSTANCE.fromDTO(userDTO);

        userService.checkUserIsLegalForRegistration(user, password);
        mailService.sendRegistrationLink(user.getEmail(), user, password);

        return new RequestResult(true, "Письмо для подтверждения регистрации послано");
    }

    @PostMapping("/send_notification_password_changed")
    public RequestResult sendNotificationPasswordChanged(@RequestBody String email) throws MessagingException {
        mailService.sendNotificationAboutPasswordChange(email);

        return new RequestResult(true, "Письмо, оповещающее о смене пароля, послано");
    }

    @PostMapping("/send_password_restoration")
    public RequestResult sendPasswordRestoration(@RequestBody String email) throws UserException, MessagingException {
        User user = userService.getUserByEmail(email);
        if (user == null)
            throw new UserException("Пользователь с таким Email не зарегистрирован");

        String token = userService.createTokenFromUser(user);

        mailService.sendPasswordRestoration(email, token);

        return new RequestResult(true, "Письмо для восстановления пароля послано");
    }

    @ExceptionHandler(UserException.class)
    public RequestResult handleException(UserException exception) {
        return new RequestResult(false, exception.getMessage());
    }

    @ExceptionHandler(MailSendException.class)
    public RequestResult handleException(MailSendException exception) {
        System.out.println(exception);
        return new RequestResult(false, "Скорее всего, Email не существует. Но возможна также и серверная ошибка");
    }

    @ExceptionHandler(MessagingException.class)
    public RequestResult handleException(MessagingException exception) {
        System.out.println(exception);
        return new RequestResult(false, "Ошибка при отправке сообщения");
    }
}
