package com.ga.homeworks.todoApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "profiles")
public class UserProfile {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    private String firstName;

    @Column
    @Getter
    @Setter
    private String lastName;

    @Column
    @Getter
    @Setter
    private String profileDescription;

    @JsonIgnore
    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

}
