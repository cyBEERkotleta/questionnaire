package com.app.questionnaire.additional.tokenable;

import com.app.questionnaire.model.dto.FormDTO;
import com.app.questionnaire.model.dto.TopicDTO;
import lombok.Data;

@Data
public class TokenWithFormAndTopic {
    private String token;
    private FormDTO form;
    private TopicDTO topic;
}
