package com.app.questionnaire.model.repository;

import com.app.questionnaire.model.entity.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * доступ к хранилищу тем для анкет
 * (таблица topics)
 *
 * @author Катя Левкович
 * @version 1.0, 26.06.2023
 */
@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {
    @Override
    public List<Topic> findAll();

    public Topic getTopicById(Long id);
}