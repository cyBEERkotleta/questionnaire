package com.app.questionnaire.model.validator;

import com.app.questionnaire.exception.TopicException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.entity.Topic;

/**
 * проверка данных сущности Topic на корректность
 *
 * @author Катя Левкович
 * @version 1.0, 05.07.2023
 */
public class TopicValidator implements IValidator<Topic> {
    private static final TopicValidator INSTANCE = new TopicValidator();

    private TopicValidator() {
    }

    public static TopicValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public void checkValidityOrThrown(Topic topic) throws TopicException {
        checkName(topic.getName());
        checkDescription(topic.getDescription());
    }

    private void checkName(String name) throws TopicException {
        if (isLengthOutsideRange(name, 3, 100))
            throw new TopicException("Название темы должно быть от 3 до 100 символов");
    }

    private void checkDescription(String firstName) throws TopicException {
        if (isLengthOutsideRange(firstName, 0, 250))
            throw new TopicException("Описание темы не должно превышать 250 символов");
    }
}