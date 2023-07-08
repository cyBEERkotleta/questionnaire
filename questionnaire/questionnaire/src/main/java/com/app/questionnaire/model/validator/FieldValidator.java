package com.app.questionnaire.model.validator;

import com.app.questionnaire.exception.FieldException;
import com.app.questionnaire.model.entity.*;

/**
 * проверка данных сущности Field на корректность
 *
 * @author Катя Левкович
 * @version 1.0, 05.07.2023
 */
public class FieldValidator implements IValidator<Field> {
    private static final FieldValidator INSTANCE = new FieldValidator();

    private FieldValidator() {
    }

    public static FieldValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public void checkValidityOrThrown(Field field) throws FieldException {
        checkLabelOrThrown(field.getLabel());
        checkTypeOrThrown(field.getFieldType());
        checkFormOrThrown(field.getForm());
    }

    private void checkLabelOrThrown(String label) throws FieldException {
        if (isLengthOutsideRange(label, 2, 100))
            throw new FieldException("Подпись поля должна быть от 2 до 100 символов");
    }

    private void checkTypeOrThrown(FieldType fieldType) throws FieldException {
        if (fieldType == null)
            throw new FieldException("Полю должен быть задан тип");
    }

    private void checkFormOrThrown(Form form) throws FieldException {
        if (form == null)
            throw new FieldException("Поле должно принадлежать к определённой форме");
    }
}