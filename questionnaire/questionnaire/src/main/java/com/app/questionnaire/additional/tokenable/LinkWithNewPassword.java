package com.app.questionnaire.additional.tokenable;

import lombok.Data;

@Data
public class LinkWithNewPassword {
    private String token;
    private String newPassword;
}
