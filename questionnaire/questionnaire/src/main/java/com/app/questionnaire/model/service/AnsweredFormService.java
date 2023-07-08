package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.AnsweredForm;
import com.app.questionnaire.model.entity.Topic;
import com.app.questionnaire.model.repository.AnsweredFormRepository;
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
        answeredFormRepository.deleteById(id);
    }

    @Override
    public AnsweredForm saveAnsweredForm(AnsweredForm answeredForm) {
        return answeredFormRepository.save(answeredForm);
    }
}