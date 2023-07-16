package com.app.questionnaire.controller;

import com.app.questionnaire.additional.tokenable.TokenWithField;
import com.app.questionnaire.additional.tokenable.TokenWithId;
import com.app.questionnaire.exception.AccessDeniedException;
import com.app.questionnaire.exception.FieldException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.additional.RequestResult;
import com.app.questionnaire.model.dto.FieldDTO;
import com.app.questionnaire.model.entity.Field;
import com.app.questionnaire.model.entity.Form;
import com.app.questionnaire.model.mappers.FieldMapper;
import com.app.questionnaire.model.service.IFieldService;
import com.app.questionnaire.model.service.IFormService;
import com.app.questionnaire.model.service.IUserService;
import com.app.questionnaire.security.AccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с полями форм
 *
 * @author Катя Левкович
 * @version 1.1, 06.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class FieldController {
    private final IFieldService fieldService;
    private final IFormService formService;
    private final IUserService userService;
    private final AccessHandler accessHandler;

    @PostMapping("/fields/{id}")
    public FieldDTO getField(@PathVariable Long id, @RequestBody String token) throws AccessDeniedException {
        Field field = fieldService.getFieldById(id);
        if (!field.getForm().getShown()) {
            accessHandler.checkUsersAreOneEntityOrThrown(token, field.getForm().getUser());
        }
        return FieldMapper.INSTANCE.toDTO(field);
    }

    @PostMapping("/fields/form_{id}")
    public List<FieldDTO> getFieldsByFormId(@PathVariable Long id, @RequestBody String token)
            throws AccessDeniedException {
        Form form = formService.getFormById(id);
        if (!form.getShown()) {
            accessHandler.checkUsersAreOneEntityOrThrown(token, form.getUser());
        }
        List<Field> fields = fieldService.getFieldsByFormId(id);
        return FieldMapper.INSTANCE.toDTOs(fields);
    }

    @PostMapping("/fields_active/form_{id}")
    public List<FieldDTO> getActiveFieldsByFormId(@PathVariable Long id, @RequestBody String token)
            throws AccessDeniedException {
        Form form = formService.getFormById(id);
        if (!form.getShown()) {
            accessHandler.checkUsersAreOneEntityOrThrown(token, form.getUser());
        }
        List<Field> fields = fieldService.getActiveFieldsByFormId(id);
        return FieldMapper.INSTANCE.toDTOs(fields);
    }

    @PostMapping("/delete_field")
    public RequestResult deleteField(@RequestBody TokenWithField tokenWithField) throws AccessDeniedException {
        Field field = fieldService.getFieldById(tokenWithField.getField().getId());
        Form form = field.getForm();
        String token = tokenWithField.getToken();
        accessHandler.checkUsersAreOneEntityOrThrown(token, form.getUser());

        fieldService.deleteFieldById(field.getId());

        return new RequestResult(true, "Поле успешно удалено");
    }

    @PostMapping("/save_field")
    public RequestResult saveField(@RequestBody TokenWithField tokenWithField) throws AccessDeniedException {
        Field field = fieldService.getFieldById(tokenWithField.getField().getId());
        Form form = field.getForm();
        String token = tokenWithField.getToken();
        accessHandler.checkUsersAreOneEntityOrThrown(token, form.getUser());

        fieldService.saveField(FieldMapper.INSTANCE.fromDTO(tokenWithField.getField()));

        return new RequestResult(true, "Поле успешно сохранено");
    }

    @ExceptionHandler(FieldException.class)
    public RequestResult handleException(FieldException exception) {
        return new RequestResult(false, exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public RequestResult handleException(AccessDeniedException exception) {
        return new RequestResult(false, "Недостаточно прав для этого действия");
    }
}