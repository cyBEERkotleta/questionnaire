package com.app.questionnaire.additional.tokenable;

import com.app.questionnaire.model.dto.FieldDTO;
import lombok.Data;

@Data
public class TokenWithField {
    private String token;
    private FieldDTO field;
}
