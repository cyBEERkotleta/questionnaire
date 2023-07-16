package com.app.questionnaire.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

/**
 * сущность - отвеченная респондентом анкета
 *
 * @author Катя Левкович
 * @version 1.1, 26.06.2023
 */
@Data
@ToString(exclude = "form")
@EqualsAndHashCode(exclude = {"form"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "answered_forms")
public class AnsweredForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "form_id")
    private Form form;

    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "answeredForm", cascade = CascadeType.ALL)
    private List<Answer> answers;
}
