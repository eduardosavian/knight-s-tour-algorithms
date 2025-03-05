package xyz.computer_algorithms.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long   id;

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @CreationTimestamp
    private LocalDateTime createdAt;
}