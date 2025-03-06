package xyz.computer_algorithms.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    private static final int[][] POSSIBLE_MOVES = {
        {-2, -1}, {-1, -2}, {1, -2}, {2, -1},
        {2, 1}, {1, 2}, {-1, 2}, {-2, 1}
    };

    private static boolean isMoveValid(int x, int y, int boardSize) {
        return x >= 0 && x < boardSize && y >= 0 && y < boardSize;
    }

    private static List<Move> findNextMoves(int x, int y, int boardSize, int[][] board, String searchType) {
        List<Move> validMoves = new ArrayList<>();

        for (int[] move : POSSIBLE_MOVES) {
            int nextX = x + move[0];
            int nextY = y + move[1];

            if (isMoveValid(nextX, nextY, boardSize) && board[nextX][nextY] == 0) {
                validMoves.add(new Move(nextX, nextY, 0));
            }
        }

        switch (searchType) {
            case "wd":
                for (Move move : validMoves) {
                    move.setPriority(findNextMoves(move.getX(), move.getY(), boardSize, board, "default").size());
                }
                validMoves.sort(Comparator.comparingInt(Move::getPriority));
                break;
            case "hd":
                for (Move move : validMoves) {
                    move.setPriority(findNextMoves(move.getX(), move.getY(), boardSize, board, "default").size());
                }
                validMoves.sort(Comparator.comparingInt(Move::getPriority).reversed());
                break;
            case "sf":
                Collections.shuffle(validMoves);
                break;
            case "dp":
                // dp
                break;
            case "ed":
                for (Move move : validMoves) {
                    int distX = Math.min(move.getX(), boardSize - 1 - move.getX());
                    int distY = Math.min(move.getY(), boardSize - 1 - move.getY());

                    move.setPriority(distX + distY);
                }

                validMoves.sort(Comparator.comparingInt(Move::getPriority));
                break;
            default:
                break;
        }
        return validMoves;
    }

    public static boolean backtrackSearch(int[][] board, int moveNum, int x, int y, int boardSize, String backtrackType) {
        board[x][y] = moveNum;

        if (moveNum == boardSize * boardSize) {
            return true;
        }

        List<Move> nextMoves = findNextMoves(x, y, boardSize, board, backtrackType);

        for (Move move : nextMoves) {
            if (backtrackSearch(board, moveNum + 1, move.getX(), move.getY(), boardSize, backtrackType)) {
                return true;
            }
        }

        board[x][y] = 0;

        return false;
    }

    public static class Move {
        private int x;
        private int y;
        private int priority;

        public Move(int x, int y, int priority) {
            this.x = x;
            this.y = y;
            this.priority = priority;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }

    public static int[][] solveKnightsTour(int boardSize, int startX, int startY, String backtrackType) {
        int[][] board = new int[boardSize][boardSize];

        if (backtrackSearch(board, 1, startX, startY, boardSize, backtrackType)) {
            return board;
        } else {
            return null; // No solution found
        }
    }
}