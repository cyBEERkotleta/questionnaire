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
        checkLabel(field.getLabel());
        checkType(field.getFieldType());
        checkForm(field.getForm());
    }

    private void checkLabel(String label) throws FieldException {
        if (isLengthOutsideRange(label, 2, 100))
            throw new FieldException("Подпись поля должна быть от 2 до 100 символов");
    }

    private void checkType(FieldType fieldType) throws FieldException {
        if (fieldType == null)
            throw new FieldException("Полю должен быть задан тип");
    }

    private void checkForm(Form form) throws FieldException {
        if (form == null)
            throw new FieldException("Поле должно принадлежать к определённой форме");
    }
}