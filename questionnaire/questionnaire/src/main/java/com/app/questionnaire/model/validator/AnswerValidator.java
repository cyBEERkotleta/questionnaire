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
        int minLength = required ? 0 : 1;
        if (isLengthOutsideRange(text, minLength, 200))
            throw new AnswerException("Ответ быть от " + minLength + " до 200 символов");
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