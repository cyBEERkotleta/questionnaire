package com.app.questionnaire.model.validator;

import com.app.questionnaire.exception.FieldException;
import com.app.questionnaire.model.entity.Field;
import com.app.questionnaire.model.entity.FieldOption;

/**
 * проверка данных сущности FieldOption на корректность
 *
 * @author Катя Левкович
 * @version 1.0, 16.07.2023
 */
public class FieldOptionValidator implements IValidator<FieldOption> {
    private static final FieldOptionValidator INSTANCE = new FieldOptionValidator();

    private FieldOptionValidator() {
    }

    public static FieldOptionValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public void checkValidityOrThrown(FieldOption fieldOption) throws FieldException {
        checkTextOrThrown(fieldOption.getText());
        checkFieldOrThrown(fieldOption.getField());
    }

    private void checkTextOrThrown(String text) throws FieldException {
        if (isLengthOutsideRange(text, 1, 200))
            throw new FieldException("Текст варианта ответа должен быть от 1 до 200 символов");
    }

    private void checkFieldOrThrown(Field field) throws FieldException {
        if (field == null)
            throw new FieldException("Вариант ответа должен принадлежать к определённому полю");
    }
}
