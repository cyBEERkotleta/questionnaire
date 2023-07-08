package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.Gender;

import java.util.List;

/**
 * абстрактный сервис для управления операциями
 * со списком полов пользователей
 *
 * @author Катя Левкович
 * @version 1.0, 07.07.2023
 */
public interface IGenderService {
    public List<Gender> findAll();
}
