package com.ga.homeworks.todoApp.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Column(unique = true)
    @Getter
    @Setter
    private String username;
    @Column(unique = true)
    @Getter
    @Setter
    private String emailAddress;
    @Column
    @Getter
    @Setter
    private String password;

    @JoinColumn(name = "profile_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private UserProfile profile;
}
