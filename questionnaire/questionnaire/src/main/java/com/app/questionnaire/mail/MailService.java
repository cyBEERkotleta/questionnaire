package com.app.questionnaire.mail;

import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.entity.Gender;
import com.app.questionnaire.model.entity.HashedPassword;
import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.service.IGenderService;
import com.app.questionnaire.model.service.IHashedPasswordService;
import com.app.questionnaire.util.Randomizer;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Service
public class MailService {
    private final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    private final IGenderService genderService;
    private final IHashedPasswordService hashedPasswordService;

    private final String SITE_URL = "localhost:4200";

    public MailService(IGenderService genderService, IHashedPasswordService hashedPasswordService) {
        this.genderService = genderService;
        this.hashedPasswordService = hashedPasswordService;

        configure();
    }

    private void configure() {
        mailSender.setPort(587);
        mailSender.setHost("smtp.gmail.com");
        mailSender.setUsername("questionnaires.softarex@gmail.com");
        mailSender.setPassword("lzfyoucyzvjehjni");
        mailSender.setProtocol("smtp");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
    }

    public void sendNotificationAboutPasswordChange(String to) throws MessagingException {
        String subject = "Изменение вашего пароля";
        String body = "Уведомляем вас, что ваш пароль на сайте опросов был изменён в " +
                getCurrentDateAndTime() + ". Если это сделали не вы, то у вас большие проблемы.";

        sendEmail(to, subject, body);
    }

    public void sendRegistrationLink(String to, User user, String password) throws MessagingException {
        String subject = "Регистрация";
        String body = "Вы отправили форму регистрации на нашем сайте в " + getCurrentDateAndTime() + ". " +
                "Чтобы завершить её, вам нужно подтвердить свой Email. " +
                "Для этого перейдите по ссылке: " +
                makeLinkClickableAndBeautiful(createLinkForRegistrationConfirmation(user, password));

        sendEmail(to, subject, body);
    }

    public void sendPasswordRestoration(String to, String token) throws MessagingException {
        String subject = "Вы забыли свой пароль";
        String body = "Вам следует уничтожить это письмо после прочтения, " +
                "а новый пароль записать в своё подсознание. Перейдите по ссылке для " +
                "безболезненной смены пароля на новый: " +
                makeLinkClickableAndBeautiful(createLinkForPasswordRestoration(token));

        sendEmail(to, subject, body);
    }

    private void sendEmail(String to, String subject, String body) throws MessagingException {
        String text = getMessageHeader() + makeBodyTextBeautiful(body) + getMessageFooter();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        helper.setTo(to);
        helper.setSubject(makeSubjectBeautiful(subject));
        helper.setText(text, true);

        /*SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(getMessageHeader() + body + getMessageFooter());*/

        mailSender.send(message);
        System.out.println("Письмо успешно отправлено на адрес " + to);
    }

    private String makeBodyTextBeautiful(String text) {
        return "<p style=\"font-size: 16px; color: black; margin-left: 50px; margin-right: 30px;\">" + text + "</p>";
    }

    private String makeSubjectBeautiful(String subject) {
        return "◆ " + subject + " ◆";
    }

    private String getMessageHeader() {
        return "<p style=\"font-size: 18px; color: darkred; font-weight: bold; text-align: center;\">Доброго дня!</p>";
    }

    private String getMessageFooter() {
        return "<p style=\"font-style: italic; font-size: 14px; text-align: center; color: #330099;\">" +
                "<br/>*    *    *" +
                "<br/><span style=\"color: deepskyblue;\">" + generateRandomSnowflakesString(25) + "</span>" +
                "<br/><span style=\"color: deepskyblue;\">" + generateRandomSnowflakesString(35) + "</span>" +
                "<br/>Гармонии, процветания и всех благ этого мира," +
                "<br/>с любовью от " + SITE_URL +
                "<br/><span style=\"color: deepskyblue;\">" + generateRandomSnowflakesString(35) + "</span>" +
                "<br/><span style=\"color: deepskyblue;\">" + generateRandomSnowflakesString(25) + "</span>" +
                "</p>";
    }

    private String makeLinkClickableAndBeautiful(String link) {
        return "<br/><p style=\"text-align: center;\">" +
                "<a href=\"" + link + "\" " +
                "style=\"font-size: 30px; color: #9900CC; text-decoration: none; " +
                "font-weight: bold; border: 2px dotted #9900CC; padding: 10px;\">" +
                "НАЖМИТЕ ДЛЯ ПОДТВЕРЖДЕНИЯ</a></p>";
    }

    private String createLinkForRegistrationConfirmation(User user, String password) {
        return "http://" + SITE_URL + "/finish-registration?email=" + user.getEmail() +
                "&hashed=" + hashedPasswordService.encrypt(password) +
                "&first_name=" + user.getFirstName() +
                "&last_name=" + user.getLastName() +
                "&phone_number=" + user.getPhoneNumber() +
                "&gender_id=" + user.getGender().getId();
    }

    private String generateRandomSnowflakesString(int length) {
        StringBuilder sb = new StringBuilder();

        String space = "&nbsp;";
        String snowflake = "❄";
        double snowflakeProbability = 0.25;

        for (int i = 0; i < length; i++) {
            if (Randomizer.doesEventHappen(snowflakeProbability)) {
                sb.append(snowflake);
            } else {
                sb.append(space);
            }
        }
        return sb.toString();
    }

    private String createLinkForPasswordRestoration(String token) {
        return "http://" + SITE_URL + "/restore?code=" + token;
    }

    private String getCurrentDateAndTime() {
        LocalTime currentTime = LocalTime.now();
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));

        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("d MMMM, yyyy"));

        return formattedTime + ", " + formattedDate;
    }
}
