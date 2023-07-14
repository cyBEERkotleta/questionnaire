package com.app.questionnaire.security;

import com.app.questionnaire.additional.HashedLoginData;
import com.app.questionnaire.additional.LoginData;
import com.app.questionnaire.exception.AccessDeniedException;
import com.app.questionnaire.model.service.IHashedPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * класс для работы с токенами сессий
 *
 * @author Катя Левкович
 * @version 1.0, 13.07.2023
 */
@Service
@RequiredArgsConstructor
public class TokenHandler {
    private final IHashedPasswordService hashedPasswordService;

    private final String SEPARATOR = "&@&&-&=&-&&@&";

    public String createTokenFromLoginData(LoginData loginData) {
        String email = loginData.getEmail();
        String hashedPassword = hashedPasswordService.encrypt(loginData.getPassword());

        return email + SEPARATOR + hashedPassword;
    }

    public String createTokenFromHashedLoginData(HashedLoginData hashedLoginData) {
        String email = hashedLoginData.getEmail();
        String hashedPassword = hashedLoginData.getHashedPassword();

        return email + SEPARATOR + hashedPassword;
    }

    public HashedLoginData getHashedLoginDataFromToken(String token) throws AccessDeniedException {
        String[] data = token.split(SEPARATOR, 2);
        if (data.length != 2)
            throw new AccessDeniedException();

        return HashedLoginData.builder()
                .email(data[0])
                .hashedPassword(data[1])
                .build();
    }
}
