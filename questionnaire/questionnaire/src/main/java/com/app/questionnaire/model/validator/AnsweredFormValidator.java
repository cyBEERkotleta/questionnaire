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
        checkForm(answeredForm.getForm());
        checkAnswers(answeredForm.getAnswers());
        checkAnswerMatching(answeredForm.getAnswers(), answeredForm.getForm());
    }

    private void checkForm(Form form) throws AnsweredFormException {
        if (form == null)
            throw new AnsweredFormException("Анкете должна быть задана форма, на которую она составлена");
    }

    private void checkAnswers(List<Answer> answers) throws AnsweredFormException {
        try {
            for (Answer answer : answers) {
                AnswerValidator.getInstance().checkValidityOrThrown(answer);
            }
        } catch (AnswerException ex) {
            throw new AnsweredFormException("Один из ответов анкеты некорректен. " + ex.getMessage());
        }
    }

    private void checkAnswerMatching(List<Answer> answers, Form form) throws AnsweredFormException {
        for (int i = 0; i < answers.size(); i++) {
            Field fieldFromAnswer = answers.get(i).getField();
            Field fieldFromForm = form.getFields().get(i);
            if (!fieldFromAnswer.equals(fieldFromForm))
                throw new AnsweredFormException("Поле ответа анкеты не соответствует полю исходной формы");
        }
    }
}