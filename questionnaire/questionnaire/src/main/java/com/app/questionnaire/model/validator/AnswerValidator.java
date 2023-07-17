package com.app.questionnaire.model.validator;

import com.app.questionnaire.exception.AnswerException;
import com.app.questionnaire.model.entity.*;

/**
 * проверка данных сущности Answer на корректность
 *
 * @author Катя Левкович
 * @version 1.0, 05.07.2023
 */
public class AnswerValidator implements IValidator<Answer> {
    private static final AnswerValidator INSTANCE = new AnswerValidator();

    private AnswerValidator() {
    }

    public static AnswerValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public void checkValidityOrThrown(Answer answer) throws AnswerException {
        checkFieldOrThrown(answer.getField());
        checkAnsweredFormOrThrown(answer.getAnsweredForm());
        checkTextOrThrown(answer.getText(), answer.getField().getRequired());
    }

    private void checkTextOrThrown(String text, boolean required) throws AnswerException {
        if (required && text.length() == 0)
            throw new AnswerException("Не все необходимые поля заполнены");

        if (isLengthOutsideRange(text, 1, 300))
            throw new AnswerException("Ответ должен быть от 1 до 300 символов");
    }

    private void checkAnsweredFormOrThrown(AnsweredForm answeredForm) throws AnswerException {
        if (answeredForm == null)
            throw new AnswerException("Ответ должен принадлежать к определённой анкете");
    }

    private void checkFieldOrThrown(Field field) throws AnswerException {
        if (field == null)
            throw new AnswerException("Ответ должен быть привязан к полю, на которое был дан");
    }
}