package com.app.questionnaire.controller;

import com.app.questionnaire.model.dto.GenderDTO;
import com.app.questionnaire.model.entity.Gender;
import com.app.questionnaire.model.mappers.GenderMapper;
import com.app.questionnaire.model.service.IGenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с различными полами пользователей
 *
 * @author Катя Левкович
 * @version 1.1, 07.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class GenderController {
    private final IGenderService genderService;

    @GetMapping("/genders")
    public List<GenderDTO> getGenders() {
        List<Gender> genders = genderService.findAll();
        return GenderMapper.INSTANCE.toDTOs(genders);
    }

    @GetMapping("/genders/{id}")
    public GenderDTO getGenderById(@PathVariable Short id) {
        Gender gender = genderService.getGenderById(id);
        return GenderMapper.INSTANCE.toDTO(gender);
    }
}