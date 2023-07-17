package com.app.questionnaire.websockets;

import com.app.questionnaire.model.dto.AnsweredFormDTO;
import com.app.questionnaire.model.entity.AnsweredForm;
import com.app.questionnaire.model.mappers.AnsweredFormMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebSocketService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendMessage(Long formId, List<AnsweredForm> answeredForms) {
        List<AnsweredFormDTO> messageContent = AnsweredFormMapper.INSTANCE.toDTOs(answeredForms);

        messagingTemplate.convertAndSend("/user/answered_forms_queue/" + formId, messageContent);
    }
}
