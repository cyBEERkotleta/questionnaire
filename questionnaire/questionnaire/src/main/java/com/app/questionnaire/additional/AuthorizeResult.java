package com.app.questionnaire.additional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * совокупность статуса ответа и токена для авторизации
 *
 * @author Катя Левкович
 * @version 1.0, 13.07.2023
 */
@Data
@AllArgsConstructor
public class AuthorizeResult {
    private boolean success;
    private String message;
    private String token;
}
