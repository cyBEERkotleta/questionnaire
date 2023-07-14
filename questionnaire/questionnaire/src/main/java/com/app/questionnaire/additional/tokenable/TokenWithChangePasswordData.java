package com.app.questionnaire.additional.tokenable;

import lombok.Data;

/**
 * класс, необходимый для корректной передачи
 * совокупности токена, Email-а пользователя,
 * нового и старого пароля
 *
 * @author Катя Левкович
 * @version 1.1, 11.07.2023
 */
@Data
public class TokenWithChangePasswordData {
    private String token;
    private String oldPassword;
    private String newPassword;
}
