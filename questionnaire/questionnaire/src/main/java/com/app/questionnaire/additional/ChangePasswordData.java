package com.app.questionnaire.additional;

import com.app.questionnaire.model.entity.User;
import lombok.Data;

/**
 * класс, необходимый для корректной передачи
 * совокупности Email-а пользователя, нового и старого пароля
 * между front-end и back-end
 * как единого объекта
 *
 * @author Катя Левкович
 * @version 1.0, 11.07.2023
 */
@Data
public class ChangePasswordData {
    private String email;
    private String oldPassword;
    private String newPassword;
}
