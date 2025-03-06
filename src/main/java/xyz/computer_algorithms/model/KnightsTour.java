package xyz.computer_algorithms.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "knights_tour")
public class KnightsTour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "algorithm", nullable = false, length = 2)
    private String algorithm;

    @Column(name = "boardSize", nullable = false)
    private Long boardSize;

    @Column(name = "start_x", nullable = false)
    private Long startX;

    @Column(name = "start_y", nullable = false)
    private Long startY;

    @Column(name = "steps")
    private Long steps;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    private static final Long[][] POSSIBLE_MOVES = {
        {-2L, -1L}, {-1L, -2L}, {1L, -2L}, {2L, -1L},
        {2L, 1L}, {1L, 2L}, {-1L, 2L}, {-2L, 1L}
    };


    private static boolean isMoveValid(Long x, Long y, Long boardSize) {
        return x >= 0 && x < boardSize && y >= 0 && y < boardSize;
    }

}