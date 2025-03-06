package xyz.computer_algorithms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import xyz.computer_algorithms.model.KnightsTour;

@Service
public interface KnightsTourService {
    KnightsTour save(KnightsTour knightsTour);

    List<KnightsTour> findAll();

    Optional<KnightsTour> findById(Long id);

    KnightsTour update(KnightsTour knightsTour);

    void deleteById(Long id);
}
