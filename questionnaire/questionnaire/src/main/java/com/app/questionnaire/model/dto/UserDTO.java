package com.app.questionnaire.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object для сущности пользователя,
 * позволяет передавать данные об объектах
 * и не впадать в рекурсивные подгрузки
 * ссылающихся друг на друга объектов
 *
 * @author Катя Левкович
 * @version 1.0, 27.06.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserRoleDTO role;
    private GenderDTO gender;
    private List<FormDTO> forms;
}
