package com.app.questionnaire.model.service;

import com.app.questionnaire.exception.TopicException;
import com.app.questionnaire.model.entity.Topic;
import com.app.questionnaire.model.repository.TopicRepository;
import com.app.questionnaire.model.validator.TopicValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * конкретный сервис для управления операциями с темами форм
 *
 * @author Катя Левкович
 * @version 1.0, 27.06.2023
 */
@Service
@RequiredArgsConstructor
public class TopicService implements ITopicService {
    private final TopicRepository topicRepository;

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic getTopicById(Long id) {
        return topicRepository.getTopicById(id);
    }

    @Override
    public void deleteTopicById(Long id) {
        topicRepository.deleteById(id);
    }

    @Override
    public Topic saveTopic(Topic topic) throws TopicException {
        TopicValidator.getInstance().checkValidityOrThrown(topic);

        return topicRepository.save(topic);
    }
}