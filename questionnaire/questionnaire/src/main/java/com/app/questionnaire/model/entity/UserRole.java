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
 * тип роли пользователей на сайте
 *
 * @author Катя Левкович
 * @version 1.3, 25.06.2023
 */
@Data
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = {"users"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(name = "name")
    private String name;

    @Column(name = "shown_name")
    private String shownName;

    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    public static UserRole admin() {
        return UserRole.builder()
                .id((short) 2)
                .name("ADMIN")
                .shownName("Администратор")
                .build();
    }

    public static UserRole member() {
        return UserRole.builder()
                .id((short) 1)
                .name("MEMBER")
                .shownName("Пользователь")
                .build();
    }
}
