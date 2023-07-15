package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.Gender;
import com.app.questionnaire.model.repository.GenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * конкретный сервис для управления операциями
 * со списком полов пользователей
 *
 * @author Катя Левкович
 * @version 1.1, 07.07.2023
 */
@Service
@RequiredArgsConstructor
public class GenderService implements IGenderService {
    private final GenderRepository genderRepository;

    @Override
    public List<Gender> findAll() {
        return genderRepository.findAll();
    }

    @Override
    public Gender getGenderById(Short id) {
        return genderRepository.getGenderById(id);
    }
}
