package com.app.questionnaire.additional;

import com.app.questionnaire.model.dto.UserDTO;
import lombok.Data;

/**
 * класс, необходимый для корректной передачи
 * совокупности пользователя и пароля
 * между front-end и back-end
 * как единого объекта
 *
 * @author Катя Левкович
 * @version 1.0, 09.07.2023
 */
@Data
public class UserWithPassword {
    private UserDTO user;
    private String password;
}
