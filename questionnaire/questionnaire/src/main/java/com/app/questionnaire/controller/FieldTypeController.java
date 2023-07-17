package com.app.questionnaire.controller;

import com.app.questionnaire.model.dto.FieldTypeDTO;
import com.app.questionnaire.model.entity.FieldType;
import com.app.questionnaire.model.mappers.FieldTypeMapper;
import com.app.questionnaire.model.service.IFieldTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с различными типами полей
 *
 * @author Катя Левкович
 * @version 1.0, 16.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class FieldTypeController {
    private final IFieldTypeService fieldTypeService;

    @GetMapping("/field_types")
    public List<FieldTypeDTO> getFieldTypes() {
        List<FieldType> fieldTypes = fieldTypeService.findAll();
        return FieldTypeMapper.INSTANCE.toDTOs(fieldTypes);
    }

    @GetMapping("/field_types/{id}")
    public FieldTypeDTO getFieldTypeById(@PathVariable Short id) {
        FieldType fieldType = fieldTypeService.getFieldTypeById(id);
        return FieldTypeMapper.INSTANCE.toDTO(fieldType);
    }
}