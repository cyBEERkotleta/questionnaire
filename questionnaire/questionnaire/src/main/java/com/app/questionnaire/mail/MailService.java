package com.app.questionnaire.mail;

import com.app.questionnaire.exception.MailException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.entity.Gender;
import com.app.questionnaire.model.entity.HashedPassword;
import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.service.IGenderService;
import com.app.questionnaire.model.service.IHashedPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender = new JavaMailSenderImpl();
    private final IGenderService genderService;
    private final IHashedPasswordService hashedPasswordService;

    private final String SITE_URL = "localhost:4200";

    public void sendNotificationAboutPasswordChange(String to) {
        String subject = "Изменение вашего пароля";
        String body = "Уведомляем вас, что ваш пароль на сайте опросов был изменён. " +
                "Если это сделали не вы, то у вас большие проблемы.";

        sendEmail(to, subject, body);
    }

    public void sendRegistrationLink(String to, User user, String password) {
        String subject = "Регистрация";
        String body = "Вы отправили форму регистрации на нашем сайте. " +
                "Чтобы завершить её, вам нужно подтвердить свой Email. " +
                "Для этого перейдите по ссылке: " + createLinkForRegistrationConfirmation(user, password);

        sendEmail(to, subject, body);
    }

    public void sendPasswordRestoration(String to, String token) {
        String subject = "Вы забыли свой пароль";
        String body = "Вам следует уничтожить это письмо после прочтения, " +
                "а новый пароль записать в своё подсознание. Перейдите по ссылке для " +
                "безболезненной смены пароля на новый: " + createLinkForPasswordRestoration(token);

        sendEmail(to, subject, body);
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(getMessageHeader() + body + getMessageFooter());

        mailSender.send(message);
        System.out.println("Письмо успешно отправлено на адрес " + to);
    }

    private String getMessageHeader() {
        return "Доброго дня!\n";
    }

    private String getMessageFooter() {
        return "\n\n-------------------------------" +
                "\nХорошего дня," +
                "\n" + SITE_URL;
    }

    private String createLinkForRegistrationConfirmation(User user, String password) {
        return SITE_URL + "/finish-registration?email:" + user.getEmail() +
                "&hashed:" + hashedPasswordService.encrypt(password) +
                "&first_name:" + user.getFirstName() +
                "&last_name:" + user.getLastName() +
                "&phone_number:" + user.getPhoneNumber() +
                "&gender_id:" + user.getGender().getId();
    }

    public User createUserFromLinkForRegistrationConfirmation(String link) throws UserException {
        String beginning = SITE_URL + "/finish-registration?email:";
        link = cutOffBeginningFromStr(link, beginning);

        String email = cutUntil(link, "&hashed:");
        link = cutOffBeginningFromStr(link, "&hashed:");

        String hashedPassword = cutUntil(link, "&first_name:");
        link = cutOffBeginningFromStr(link, "&first_name:");

        String firstName = cutUntil(link, "&last_name:");
        link = cutOffBeginningFromStr(link, "&last_name:");

        String lastName = cutUntil(link, "&phone_number:");
        link = cutOffBeginningFromStr(link, "&phone_number:");

        String phoneNumber = cutUntil(link, "&gender_id:");
        link = cutOffBeginningFromStr(link, "&gender_id:");

        String genderIdStr = link;
        Gender gender = getGenderStrByIdOrThrown(genderIdStr);

        HashedPassword hashedPasswordObj = HashedPassword.builder()
                .hash(hashedPassword)
                .build();

        return User.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .hashedPassword(hashedPasswordObj)
                .build();
    }

    private String createLinkForPasswordRestoration(String token) {
        return SITE_URL + "/restore?" + token;
    }

    public String createTokenFromLinkForPasswordRestoration(String link) {
        String beginning = SITE_URL + "/restore?";
        return cutOffBeginningFromStr(link, beginning);
    }

    private Gender getGenderStrByIdOrThrown(String strId) throws UserException {
        Short id;
        try {
            id = Short.valueOf(strId);
        } catch (NumberFormatException ex) {
            throw new UserException("Некорректная роль пользователя");
        }

        Gender gender = genderService.getGenderById(id);
        if (gender == null)
            throw new UserException("Некорректная роль пользователя");
        return gender;
    }

    private String cutOffBeginningFromStr(String source, String beginning) {
        return source.substring(source.indexOf(beginning) + beginning.length());
    }

    private String cutUntil(String source, String substrEnd) {
        int index = source.indexOf(substrEnd);
        return source.substring(0, index);
    }
}
