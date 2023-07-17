package com.app.questionnaire.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * тип поля для ввода
 * (ComboBox, RadioButton и другие)
 *
 * @author Катя Левкович
 * @version 1.2, 25.06.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "field_types")
public class FieldType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(name = "name")
    private String name;

    @Column(name = "able_to_have_options")
    private Boolean ableToHaveOptions;

    public static FieldType date() {
        return FieldType.builder()
                .id((short) 1)
                .name("DATE")
                .ableToHaveOptions(false)
                .build();
    }

    public static FieldType comboBox() {
        return FieldType.builder()
                .id((short) 2)
                .name("COMBOBOX")
                .ableToHaveOptions(true)
                .build();
    }

    public static FieldType checkBox() {
        return FieldType.builder()
                .id((short) 3)
                .name("CHECKBOX")
                .ableToHaveOptions(true)
                .build();
    }

    public static FieldType radioButton() {
        return FieldType.builder()
                .id((short) 4)
                .name("RADIOBUTTON")
                .ableToHaveOptions(true)
                .build();
    }

    public static FieldType multiLineText() {
        return FieldType.builder()
                .id((short) 5)
                .name("MULTI LINE TEXT")
                .ableToHaveOptions(false)
                .build();
    }

    public static FieldType singleLineText() {
        return FieldType.builder()
                .id((short) 6)
                .name("SINGLE LINE TEXT")
                .ableToHaveOptions(false)
                .build();
    }
}
