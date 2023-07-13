package com.app.questionnaire.controller;

import com.app.questionnaire.exception.AnsweredFormException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.RequestResult;
import com.app.questionnaire.model.dto.AnsweredFormDTO;
import com.app.questionnaire.model.entity.AnsweredForm;
import com.app.questionnaire.model.mappers.AnsweredFormMapper;
import com.app.questionnaire.model.service.IAnsweredFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с отвеченными анкетами
 *
 * @author Катя Левкович
 * @version 1.0, 06.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AnsweredFormController {
    private final IAnsweredFormService answeredFormService;

    @GetMapping("/answered_forms/{id}")
    public AnsweredFormDTO getAnsweredForm(@PathVariable Long id) {
        AnsweredForm form = answeredFormService.getAnsweredFormById(id);
        return AnsweredFormMapper.INSTANCE.toDTO(form);
    }

    @GetMapping("/answered_forms/form_{id}")
    public List<AnsweredFormDTO> getAnsweredFormsByFormId(@PathVariable Long id) {
        List<AnsweredForm> forms = answeredFormService.getAnsweredFormsByFormId(id);
        return AnsweredFormMapper.INSTANCE.toDTOs(forms);
    }

    @PostMapping("/delete_answered_form")
    public RequestResult deleteAnsweredForm(@RequestBody Long id) {
        answeredFormService.deleteAnsweredFormById(id);

        return new RequestResult(true, "Отвеченная анкета успешно удалена");
    }

    @PostMapping("/save_answered_form")
    public RequestResult saveAnsweredForm(@RequestBody AnsweredFormDTO answeredForm) {
        answeredFormService.saveAnsweredForm(AnsweredFormMapper.INSTANCE.fromDTO(answeredForm));

        return new RequestResult(true, "Отвеченная анкета успешно сохранена");
    }

    @ExceptionHandler(UserException.class)
    public RequestResult handleException(AnsweredFormException exception) {
        return new RequestResult(false, exception.getMessage());
    }
}