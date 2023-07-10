package com.app.questionnaire.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * поле для заполнения, они могут быть различных типов:
 * ComboBox, TextBox, RadioButton и другие
 *
 * @author Катя Левкович
 * @version 1.0, 25.06.2023
 */
@Data
@ToString(exclude = "form")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fields")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "label")
    private String label;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_id")
    private FieldType fieldType;

    @Column(name = "required")
    private boolean required;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "form_id")
    private Form form;
}