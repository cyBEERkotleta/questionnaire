package com.app.questionnaire.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * хэшированный пароль пользователя
 *
 * @author Катя Левкович
 * @version 1.1, 06.07.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "passwords")
public class HashedPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "hash")
    private String hash;
}