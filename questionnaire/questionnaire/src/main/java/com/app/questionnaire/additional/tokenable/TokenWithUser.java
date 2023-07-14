package com.app.questionnaire.additional.tokenable;

import com.app.questionnaire.model.dto.UserDTO;
import lombok.Data;

@Data
public class TokenWithUser {
    private String token;
    private UserDTO user;
}
