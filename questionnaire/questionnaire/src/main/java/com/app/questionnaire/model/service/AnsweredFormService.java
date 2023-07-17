package com.app.questionnaire.model.service;

import com.app.questionnaire.exception.AnsweredFormException;
import com.app.questionnaire.model.entity.AnsweredForm;
import com.app.questionnaire.model.entity.Topic;
import com.app.questionnaire.model.repository.AnsweredFormRepository;
import com.app.questionnaire.model.validator.AnsweredFormValidator;
import com.app.questionnaire.websockets.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * конкретный сервис для управления операциями
 * с отвеченными анкетами
 *
 * @author Катя Левкович
 * @version 1.0, 29.06.2023
 */
@Service
@RequiredArgsConstructor
public class AnsweredFormService implements IAnsweredFormService {
    private final AnsweredFormRepository answeredFormRepository;

    private final WebSocketService webSocketService;

    @Override
    public List<AnsweredForm> getAnsweredFormsByFormId(Long id) {
        return answeredFormRepository.getAnsweredFormsByFormId(id);
    }

    @Override
    public AnsweredForm getAnsweredFormById(Long id) {
        return answeredFormRepository.getAnsweredFormById(id);
    }

    @Override
    public void deleteAnsweredFormById(Long id) {
        Long formId = getAnsweredFormById(id).getForm().getId();
        notifyWebSocket(formId);

        answeredFormRepository.deleteById(id);
    }

    @Override
    public AnsweredForm saveAnsweredForm(AnsweredForm answeredForm) throws AnsweredFormException {
        for (int i = 0; i < answeredForm.getAnswers().size(); i++) {
            answeredForm.getAnswers().get(i).setAnsweredForm(answeredForm);
        }

        AnsweredFormValidator.getInstance().checkValidityOrThrown(answeredForm);

        for (int i = 0; i < answeredForm.getAnswers().size(); i++) {
            if (answeredForm.getAnswers().get(i).getText().length() == 0)
                answeredForm.getAnswers().get(i).setText("N/A");
        }

        AnsweredForm result = answeredFormRepository.save(answeredForm);
        Long formId = answeredForm.getForm().getId();
        notifyWebSocket(formId);

        return result;
    }

    private void notifyWebSocket(Long formId) {
        webSocketService.sendMessage(formId, getAnsweredFormsByFormId(formId));
    }
}