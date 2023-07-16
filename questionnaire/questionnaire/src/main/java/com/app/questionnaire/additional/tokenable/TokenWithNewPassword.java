package com.app.questionnaire.additional.tokenable;

import lombok.Data;

@Data
public class TokenWithNewPassword {
    private String token;
    private String newPassword;
}
