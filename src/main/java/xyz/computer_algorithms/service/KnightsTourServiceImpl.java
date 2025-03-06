package xyz.computer_algorithms.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.computer_algorithms.model.KnightsTour;
import xyz.computer_algorithms.repository.KnightsTourRepository;

@Service
public class KnightsTourServiceImpl implements KnightsTourService {
    @Autowired
    private KnightsTourRepository knightsTourRepository;

    @Override
    public KnightsTour save(KnightsTour knightsTour) {
        return knightsTourRepository.save(knightsTour);
    }

    @Override
    public List<KnightsTour> findAll() {
        return knightsTourRepository.findAll();
    }

    @Override
    public Optional<KnightsTour> findById(Long id) {
        return knightsTourRepository.findById(id);
    }

    @Override
    public KnightsTour update(KnightsTour knightsTour) {
        return knightsTourRepository.save(knightsTour);
    }

    @Override
    public void deleteById(Long id) {
        knightsTourRepository.deleteById(id);
    }

    @Override
    public int[][] solveKnightsTour(int boardSize, int startX, int startY, String backtrackType) {
        return KnightsTour.solveKnightsTour(boardSize, startX, startY, backtrackType);
    }

    public int[][] createAndSolveKnightsTour(int boardSize, int startX, int startY, String backtrackType) {
        KnightsTour kt = new KnightsTour();
        kt.setBoardSize((long) boardSize);
        kt.setStartX((long) startX);
        kt.setStartY((long) startY);
        kt.setAlgorithm(backtrackType);
        kt.setCreatedAt(LocalDateTime.now());
        knightsTourRepository.save(kt);

        return KnightsTour.solveKnightsTour(boardSize, startX, startY, backtrackType);
    }
}
