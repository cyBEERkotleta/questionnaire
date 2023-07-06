package com.app.questionnaire.model.validator;

/**
 * интерфейс проверки сущностей на корректность данных
 *
 * @author Катя Левкович
 * @version 1.0, 05.07.2023
 */
public interface IValidator<T> {
    public void checkValidityOrThrown(T obj) throws Exception;

    default boolean isLengthOutsideRange(String str, int minLength, int maxLength) {
        return str.length() < minLength || str.length() > maxLength;
    }
}