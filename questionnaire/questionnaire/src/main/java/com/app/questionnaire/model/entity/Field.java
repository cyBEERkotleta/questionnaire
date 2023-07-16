package com.app.questionnaire.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

/**
 * поле для заполнения, они могут быть различных типов:
 * ComboBox, TextBox, RadioButton и другие
 *
 * @author Катя Левкович
 * @version 1.1, 25.06.2023
 */
@Data
@ToString(exclude = "form")
@EqualsAndHashCode(exclude = {"form"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fields")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "label")
    private String label;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id")
    private FieldType fieldType;

    @Column(name = "required")
    private Boolean required;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne(optional = false)
    @JoinColumn(name = "form_id")
    private Form form;

    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<FieldOption> options = new ArrayList<>();
}