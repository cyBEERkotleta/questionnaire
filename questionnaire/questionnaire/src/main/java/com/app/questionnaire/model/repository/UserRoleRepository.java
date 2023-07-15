package com.app.questionnaire.model.repository;

import com.app.questionnaire.model.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * доступ к хранилищу типов ролей пользователей
 *
 * @author Катя Левкович
 * @version 1.0, 07.07.2023
 */
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Short> {
    @Override
    public List<UserRole> findAll();

    public UserRole getUserRoleById(Short id);
}
