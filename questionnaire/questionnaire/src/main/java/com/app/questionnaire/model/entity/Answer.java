package com.app.questionnaire.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * ответ на поле для ввода
 *
 * @author Катя Левкович
 * @version 1.0, 25.06.2023
 */
@Data
@ToString(exclude = "answeredForm")
@EqualsAndHashCode(exclude = {"answeredForm"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "answered_form_id")
    private AnsweredForm answeredForm;

    @ManyToOne(optional = false)
    @JoinColumn(name = "field_id")
    private Field field;
}