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
 * форма (анкета) с вопросами
 *
 * @author Катя Левкович
 * @version 1.2, 25.06.2023
 */
@Data
@ToString(exclude = {"topic", "user"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "forms")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "shown")
    private boolean shown;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<AnsweredForm> answeredForms = new ArrayList<>();

    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<Field> fields = new ArrayList<>();
}
