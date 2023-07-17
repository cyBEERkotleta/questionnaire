package com.app.questionnaire.model.repository;

import com.app.questionnaire.model.entity.FieldType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * доступ к хранилищу типов полей
 *
 * @author Катя Левкович
 * @version 1.0, 16.07.2023
 */
@Repository
public interface FieldTypeRepository extends CrudRepository<FieldType, Short> {
    @Override
    public List<FieldType> findAll();

    public FieldType getFieldTypeById(Short id);
}
