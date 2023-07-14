package com.app.questionnaire.additional;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * результат запроса
 *
 * @author Катя Левкович
 * @version 1.0, 05.07.2023
 */
@Data
@AllArgsConstructor
public class RequestResult {
    private boolean success;
    private String message;
}
