package com.app.questionnaire.model.validator;

import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.entity.Gender;
import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.entity.UserRole;

import java.util.regex.Pattern;

/**
 * проверка данных сущности User на корректность
 *
 * @author Катя Левкович
 * @version 1.0, 05.07.2023
 */
public class UserValidator implements IValidator<User> {
    private static final UserValidator INSTANCE = new UserValidator();

    private UserValidator() {
    }

    public static UserValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public void checkValidityOrThrown(User user) throws UserException {
        checkEmailOrThrown(user.getEmail());
        checkFirstNameOrThrown(user.getFirstName());
        checkLastNameOrThrown(user.getLastName());
        checkPhoneNumberOrThrown(user.getPhoneNumber());
        checkGenderOrThrown(user.getGender());
        checkUserRoleOrThrown(user.getUserRole());
    }

    public void checkPasswordOrThrown(String password) throws UserException {
        if (isLengthOutsideRange(password, 8, 30))
            throw new UserException("Пароль должен быть от 8 до 30 символов");
    }

    private void checkEmailOrThrown(String email) throws UserException {
        if (!isStrAnEmail(email))
            throw new UserException("Укажите корректный формат Email");

        if (isLengthOutsideRange(email, 5, 60))
            throw new UserException("Email должен быть от 5 до 60 символов");
    }

    private void checkFirstNameOrThrown(String firstName) throws UserException {
        if (isLengthOutsideRange(firstName, 2, 30))
            throw new UserException("Имя должно быть от 2 до 30 символов");
    }

    private void checkLastNameOrThrown(String lastName) throws UserException {
        if (isLengthOutsideRange(lastName, 2, 30))
            throw new UserException("Фамилия должна быть от 2 до 30 символов");
    }

    private void checkPhoneNumberOrThrown(String phoneNumber) throws UserException {
        if (isLengthOutsideRange(phoneNumber, 3, 15))
            throw new UserException("Телефонный номер должен быть от 3 до 15 символов");
    }

    private void checkGenderOrThrown(Gender gender) throws UserException {
        if (gender == null)
            throw new UserException("Пользователю не задан пол");
    }

    private void checkUserRoleOrThrown(UserRole userRole) throws UserException {
        if (userRole == null)
            throw new UserException("Пользователю не задана роль");
    }

    private boolean isStrAnEmail(String str) {
        Pattern pattern = Pattern.compile("\\w+@\\w+\\.\\w+");
        //xxx@yyy.zzz, где x, y, z - любое кол-во буквенно-цифровых символов или символов подчёркивания

        return pattern.matcher(str).matches();
    }
}
