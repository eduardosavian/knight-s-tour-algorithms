package xyz.computer_algorithms.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "knights_tour")
public class KnightsTour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "requisition_id")
    private Requisition requisition;

    @Column(name = "algorithm", nullable = false, length = 2)
    private String algorithm;

    @Column(name = "boardSize", nullable = false)
    private Integer boardSize;

    @Column(name = "start_x", nullable = false)
    private Integer startX;

    @Column(name = "start_y", nullable = false)
    private Integer startY;

    @Column(name = "steps", nullable = false)
    private Integer steps;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}