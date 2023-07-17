package com.app.questionnaire.controller;

import com.app.questionnaire.additional.tokenable.TokenWithForm;
import com.app.questionnaire.exception.AccessDeniedException;
import com.app.questionnaire.exception.FormException;
import com.app.questionnaire.additional.RequestResult;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.dto.FormDTO;
import com.app.questionnaire.model.dto.TopicDTO;
import com.app.questionnaire.model.entity.Form;
import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.mappers.FormMapper;
import com.app.questionnaire.model.mappers.TopicMapper;
import com.app.questionnaire.model.service.IFormService;
import com.app.questionnaire.model.service.IUserService;
import com.app.questionnaire.security.AccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с формами (опросниками)
 *
 * @author Катя Левкович
 * @version 1.1, 06.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class FormController {
    private final IFormService formService;
    private final IUserService userService;
    private final AccessHandler accessHandler;

    @GetMapping("/forms")
    public List<FormDTO> getForms() {
        List<Form> forms = formService.findAll();
        return FormMapper.INSTANCE.toDTOs(forms);
    }

    @PostMapping("/forms/{id}")
    public FormDTO getForm(@PathVariable Long id, @RequestBody String token) throws AccessDeniedException {
        Form form = formService.getFormById(id);
        if (!form.getShown()) {
            accessHandler.checkUsersAreOneEntityOrThrown(token, form.getUser());
        }

        return FormMapper.INSTANCE.toDTO(form);
    }

    @GetMapping("/forms/topic_{id}")
    public List<FormDTO> getFormsByTopicId(@PathVariable Long id) {
        List<Form> forms = formService.getFormsByTopicId(id);
        return FormMapper.INSTANCE.toDTOs(forms);
    }

    @PostMapping("/forms/user_{id}")
    public List<FormDTO> getFormsByUserId(@PathVariable Long id, @RequestBody String token) throws AccessDeniedException {
        User user = userService.getUserById(id);
        accessHandler.checkUsersAreOneEntityOrThrown(token, user);

        List<Form> forms = formService.getFormsByUserId(id);
        return FormMapper.INSTANCE.toDTOs(forms);
    }

    @PostMapping("/delete_form")
    public RequestResult deleteForm(@RequestBody TokenWithForm tokenWithForm) throws AccessDeniedException {
        Form form = formService.getFormById(tokenWithForm.getForm().getId());
        if (!accessHandler.areUsersOneEntity(tokenWithForm.getToken(), form.getUser())) {
            accessHandler.checkTokenIsFromAdminAccountOrThrown(tokenWithForm.getToken());
            if (!form.getShown())
                throw new AccessDeniedException();
        }

        formService.deleteFormById(tokenWithForm.getForm().getId());

        return new RequestResult(true, "Форма успешно удалена");
    }

    @PostMapping("/save_form")
    public RequestResult saveForm(@RequestBody TokenWithForm tokenWithForm)
            throws AccessDeniedException, FormException {
        FormDTO form = tokenWithForm.getForm();
        String token = tokenWithForm.getToken();

        if (form.getId() != null) {
            Form formWas = formService.getFormById(form.getId());
            accessHandler.checkUsersAreOneEntityOrThrown(token, formWas.getUser());
        }

        Form formToSave = FormMapper.INSTANCE.fromDTO(form);

        formService.saveForm(formToSave);

        return new RequestResult(true, "Форма успешно сохранена");
    }

    @ExceptionHandler(FormException.class)
    public RequestResult handleException(FormException exception) {
        return new RequestResult(false, exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public RequestResult handleException(AccessDeniedException exception) {
        return new RequestResult(false, "Недостаточно прав для этого действия");
    }
}