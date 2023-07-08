package com.app.questionnaire.controller;

import com.app.questionnaire.exception.FieldException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.RequestResult;
import com.app.questionnaire.model.dto.FieldDTO;
import com.app.questionnaire.model.entity.Field;
import com.app.questionnaire.model.mappers.FieldMapper;
import com.app.questionnaire.model.service.IFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с полями форм
 *
 * @author Катя Левкович
 * @version 1.0, 06.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class FieldController {
    private final IFieldService fieldService;

    @GetMapping("/fields/{id}")
    public FieldDTO getField(@PathVariable Long id) {
        Field field = fieldService.getFieldById(id);
        return FieldMapper.INSTANCE.toDTO(field);
    }

    @GetMapping("/fields/form_{id}")
    public List<FieldDTO> getFieldsByFormId(@PathVariable Long id) {
        List<Field> fields = fieldService.getFieldsByFormId(id);
        return FieldMapper.INSTANCE.toDTOs(fields);
    }

    @GetMapping("/fields_active/form_{id}")
    public List<FieldDTO> getActiveFieldsByFormId(@PathVariable Long id) {
        List<Field> fields = fieldService.getActiveFieldsByFormId(id);
        return FieldMapper.INSTANCE.toDTOs(fields);
    }

    @PostMapping("/delete_field")
    public RequestResult deleteField(@RequestParam Long id) {
        fieldService.deleteFieldById(id);

        return new RequestResult(true, "Поле успешно удалено");
    }

    @PostMapping("/save_field")
    public RequestResult saveField(@RequestParam FieldDTO field) {
        fieldService.saveField(FieldMapper.INSTANCE.fromDTO(field));

        return new RequestResult(true, "Поле успешно сохранено");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserException.class)
    public RequestResult handleException(FieldException exception) {
        return new RequestResult(false, exception.getMessage());
    }
}