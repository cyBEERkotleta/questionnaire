package com.app.questionnaire.additional;

import lombok.Builder;
import lombok.Data;

/**
 * класс, необходимый для корректной передачи
 * совокупности email-а и пароля
 * между front-end и back-end
 * как единого объекта
 *
 * @author Катя Левкович
 * @version 1.0, 09.07.2023
 */
@Data
@Builder
public class LoginData {
    private String email;
    private String password;
}
