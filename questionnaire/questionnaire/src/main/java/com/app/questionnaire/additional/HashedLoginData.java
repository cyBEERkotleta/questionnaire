package com.app.questionnaire.additional;

import lombok.Builder;
import lombok.Data;

/**
 * совокупность данных для входа:
 * Email и хэшированный пароль
 *
 * @author Катя Левкович
 * @version 1.0, 13.07.2023
 */
@Data
@Builder
public class HashedLoginData {
    private String email;
    private String hashedPassword;
}
