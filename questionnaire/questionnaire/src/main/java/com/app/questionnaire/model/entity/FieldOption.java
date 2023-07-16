package com.app.questionnaire.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * вариант поля Field
 *
 * @author Катя Левкович
 * @version 1.0, 15.07.2023
 */
@Data
@ToString(exclude = "field")
@EqualsAndHashCode(exclude = {"field"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "field_options")
public class FieldOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "field_id")
    private Field field;
}