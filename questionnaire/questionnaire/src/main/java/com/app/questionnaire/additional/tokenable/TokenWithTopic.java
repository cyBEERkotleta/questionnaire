package com.app.questionnaire.additional.tokenable;

import com.app.questionnaire.model.dto.TopicDTO;
import lombok.Data;

@Data
public class TokenWithTopic {
    private String token;
    private TopicDTO topic;
}
