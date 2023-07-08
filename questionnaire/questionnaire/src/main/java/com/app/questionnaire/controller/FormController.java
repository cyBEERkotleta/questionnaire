package com.app.questionnaire.controller;

import com.app.questionnaire.exception.FormException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.RequestResult;
import com.app.questionnaire.model.dto.FormDTO;
import com.app.questionnaire.model.entity.Form;
import com.app.questionnaire.model.mappers.FormMapper;
import com.app.questionnaire.model.service.IFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с формами (опросниками)
 *
 * @author Катя Левкович
 * @version 1.0, 06.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class FormController {
    private final IFormService formService;

    @GetMapping("/forms")
    public List<FormDTO> getForms() {
        List<Form> forms = formService.findAll();
        return FormMapper.INSTANCE.toDTOs(forms);
    }

    @GetMapping("/forms/{id}")
    public FormDTO getForm(@PathVariable Long id) {
        Form form = formService.getFormById(id);
        return FormMapper.INSTANCE.toDTO(form);
    }

    @GetMapping("/forms/topic_{id}")
    public List<FormDTO> getFormsByTopicId(@PathVariable Long id) {
        List<Form> forms = formService.getFormsByTopicId(id);
        return FormMapper.INSTANCE.toDTOs(forms);
    }

    @GetMapping("/forms/user_{id}")
    public List<FormDTO> getFormsByUserId(@PathVariable Long id) {
        List<Form> forms = formService.getFormsByUserId(id);
        return FormMapper.INSTANCE.toDTOs(forms);
    }

    @PostMapping("/delete_form")
    public RequestResult deleteForm(@RequestParam Long id) {
        formService.deleteFormById(id);

        return new RequestResult(true, "Форма успешно удалена");
    }

    @PostMapping("/save_form")
    public RequestResult saveForm(@RequestParam FormDTO form) {
        formService.saveForm(FormMapper.INSTANCE.fromDTO(form));

        return new RequestResult(true, "Форма успешно сохранена");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserException.class)
    public RequestResult handleException(FormException exception) {
        return new RequestResult(false, exception.getMessage());
    }
}