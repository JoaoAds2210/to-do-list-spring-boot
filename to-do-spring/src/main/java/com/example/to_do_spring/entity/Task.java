package com.example.to_do_spring.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.spi.Status;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.SAVING;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
