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
 * сущность - пользователь сайта,
 * может совершать действия в зависимости от своей роли
 *
 * @author Катя Левкович
 * @version 1.2, 25.06.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "password_id", referencedColumnName = "id")
    private HashedPassword hashedPassword;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private UserRole role;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Form> forms = new ArrayList<>();
}
