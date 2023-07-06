package com.app.questionnaire.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * шифровальщик паролей,
 * хэширование
 *
 * @author Катя Левкович
 * @version 1.0, 05.07.2023
 */
public final class PasswordEncryptor {
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}