package com.app.questionnaire.model.validator;

import com.app.questionnaire.exception.AnswerException;
import com.app.questionnaire.exception.AnsweredFormException;
import com.app.questionnaire.model.entity.Answer;
import com.app.questionnaire.model.entity.AnsweredForm;
import com.app.questionnaire.model.entity.Field;
import com.app.questionnaire.model.entity.Form;

import java.util.List;

/**
 * проверка данных сущности AnsweredForm на корректность
 *
 * @author Катя Левкович
 * @version 1.0, 05.07.2023
 */
public class AnsweredFormValidator implements IValidator<AnsweredForm> {
    private static final AnsweredFormValidator INSTANCE = new AnsweredFormValidator();

    private AnsweredFormValidator() {
    }

    public static AnsweredFormValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public void checkValidityOrThrown(AnsweredForm answeredForm) throws AnsweredFormException {
        checkFormOrThrown(answeredForm.getForm());
        checkAnswersOrThrown(answeredForm.getAnswers());
    }

    private void checkFormOrThrown(Form form) throws AnsweredFormException {
        if (form == null)
            throw new AnsweredFormException("Анкете должна быть задана форма, на которую она составлена");
    }

    private void checkAnswersOrThrown(List<Answer> answers) throws AnsweredFormException {
        if (answers.size() == 0)
            throw new AnsweredFormException("Нельзя записать результат прохождения анкеты без единого ответа");

        try {
            for (Answer answer : answers) {
                AnswerValidator.getInstance().checkValidityOrThrown(answer);
            }
        } catch (AnswerException ex) {
            throw new AnsweredFormException("Один из ответов анкеты некорректен. " + ex.getMessage());
        }
    }
}