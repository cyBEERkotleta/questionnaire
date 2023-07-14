package com.app.questionnaire.additional.tokenable;

import com.app.questionnaire.model.dto.FormDTO;
import lombok.Data;

@Data
public class TokenWithForm {
    private String token;
    private FormDTO form;
}
