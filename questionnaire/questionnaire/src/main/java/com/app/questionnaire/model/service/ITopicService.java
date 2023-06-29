package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.Topic;

import java.util.List;

/**
 * абстрактный сервис для управления операциями с темами форм
 *
 * @author Катя Левкович
 * @version 1.0, 27.06.2023
 */
public interface ITopicService {
    public List<Topic> findAll();
    public Topic getTopicById(Long id);
    public void deleteTopicById(Long id);
    public Topic saveTopicAndCreateId(Topic topic);
}
