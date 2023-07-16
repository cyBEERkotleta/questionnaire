package com.app.questionnaire.additional;

import com.app.questionnaire.model.entity.User;
import lombok.Data;

@Data
public class UserWithHashedPassword {
    private User user;
    private String hashedPassword;
}
