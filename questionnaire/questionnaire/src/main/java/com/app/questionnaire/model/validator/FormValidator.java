package com.app.questionnaire.model.validator;

import com.app.questionnaire.exception.FormException;
import com.app.questionnaire.model.entity.Form;
import com.app.questionnaire.model.entity.Topic;
import com.app.questionnaire.model.entity.User;

/**
 * проверка данных сущности Form на корректность
 *
 * @author Катя Левкович
 * @version 1.0, 05.07.2023
 */
public class FormValidator implements IValidator<Form> {
    private static final FormValidator INSTANCE = new FormValidator();

    private FormValidator() {
    }

    public static FormValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public void checkValidityOrThrown(Form form) throws FormException {
        checkName(form.getName());
        checkTopic(form.getTopic());
        checkUser(form.getUser());
    }

    private void checkName(String name) throws FormException {
        if (isLengthOutsideRange(name, 2, 100))
            throw new FormException("Название формы должно быть от 2 до 100 символов");
    }

    private void checkTopic(Topic topic) throws FormException {
        if (topic == null)
            throw new FormException("Форма должна принадлежать к одной из тем");
    }

    private void checkUser(User user) throws FormException {
        if (user == null)
            throw new FormException("У формы должен быть обозначен её пользователь-создатель");
    }
}