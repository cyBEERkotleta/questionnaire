package com.app.questionnaire.model.validator;

import com.app.questionnaire.exception.FieldException;
import com.app.questionnaire.model.entity.*;

import java.util.List;

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
        checkTypeOrThrown(field.getType());
        checkFormOrThrown(field.getForm());

        checkOptionsOrThrown(field.getType(), field.getOptions());
    }

    private void checkLabelOrThrown(String label) throws FieldException {
        if (isLengthOutsideRange(label, 2, 300))
            throw new FieldException("Подпись поля должна быть от 2 до 300 символов");
    }

    private void checkTypeOrThrown(FieldType fieldType) throws FieldException {
        if (fieldType == null)
            throw new FieldException("Полю должен быть задан тип");
    }

    private void checkFormOrThrown(Form form) throws FieldException {
        if (form == null)
            throw new FieldException("Поле должно принадлежать к определённой форме");
    }

    private void checkOptionsOrThrown(FieldType type, List<FieldOption> options) throws FieldException {
        if (type.getAbleToHaveOptions() && options.size() == 0)
            throw new FieldException("Этот тип поля должен иметь хотя бы один вариант ответа");
        if (!type.getAbleToHaveOptions() && options.size() > 0)
            throw new FieldException("Этот тип поля не может иметь варианты ответа");

        for (FieldOption option : options) {
            FieldOptionValidator.getInstance().checkValidityOrThrown(option);
        }
    }
}