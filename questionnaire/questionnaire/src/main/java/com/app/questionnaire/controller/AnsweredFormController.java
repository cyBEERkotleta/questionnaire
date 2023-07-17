package com.app.questionnaire.controller;

import com.app.questionnaire.exception.AccessDeniedException;
import com.app.questionnaire.exception.AnsweredFormException;
import com.app.questionnaire.additional.RequestResult;
import com.app.questionnaire.model.dto.AnsweredFormDTO;
import com.app.questionnaire.model.entity.AnsweredForm;
import com.app.questionnaire.model.entity.Form;
import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.mappers.AnsweredFormMapper;
import com.app.questionnaire.model.service.IAnsweredFormService;
import com.app.questionnaire.model.service.IFormService;
import com.app.questionnaire.model.service.IUserService;
import com.app.questionnaire.security.AccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с отвеченными анкетами
 *
 * @author Катя Левкович
 * @version 1.1, 06.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AnsweredFormController {
    private final IAnsweredFormService answeredFormService;
    private final IFormService formService;
    private final AccessHandler accessHandler;

    @PostMapping("/answered_forms/{id}")
    public AnsweredFormDTO getAnsweredForm(@PathVariable Long id, @RequestBody String token)
            throws AccessDeniedException {
        AnsweredForm form = answeredFormService.getAnsweredFormById(id);
        User user = form.getForm().getUser();
        accessHandler.checkUsersAreOneEntityOrThrown(token, user);

        return AnsweredFormMapper.INSTANCE.toDTO(form);
    }

    @PostMapping("/answered_forms/form_{id}")
    public List<AnsweredFormDTO> getAnsweredFormsByFormId(@PathVariable Long id, @RequestBody String token)
            throws AccessDeniedException {
        Form form = formService.getFormById(id);
        User user = form.getUser();
        accessHandler.checkUsersAreOneEntityOrThrown(token, user);

        List<AnsweredForm> forms = answeredFormService.getAnsweredFormsByFormId(id);
        return AnsweredFormMapper.INSTANCE.toDTOs(forms);
    }

    @PostMapping("/save_answered_form")
    public RequestResult saveAnsweredForm(@RequestBody AnsweredFormDTO answeredForm) throws AnsweredFormException {
        answeredFormService.saveAnsweredForm(AnsweredFormMapper.INSTANCE.fromDTO(answeredForm));

        return new RequestResult(true, "Отвеченная анкета успешно сохранена");
    }

    @ExceptionHandler(AnsweredFormException.class)
    public RequestResult handleException(AnsweredFormException exception) {
        return new RequestResult(false, exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public RequestResult handleException(AccessDeniedException exception) {
        return new RequestResult(false, "Недостаточно прав для этого действия");
    }
}