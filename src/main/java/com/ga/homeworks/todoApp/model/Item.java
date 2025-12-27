package com.ga.homeworks.todoApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "items")
public class Item {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter @Setter private String name;

    @Column
    @Getter @Setter private String description;

    @Column
    @Getter @Setter private LocalDateTime dueDate;

    @Column
    @CreationTimestamp
    @Getter @Setter private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    @Getter @Setter private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    @Getter @Setter private Category category;
}
