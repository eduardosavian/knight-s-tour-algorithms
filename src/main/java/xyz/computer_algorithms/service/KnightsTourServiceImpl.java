package xyz.computer_algorithms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.computer_algorithms.model.KnightsTour;
import xyz.computer_algorithms.repository.KnightsTourRepository;

@RequiredArgsConstructor
@Service
public class KnightsTourServiceImpl implements KnightsTourService {
    private final KnightsTourRepository knightsTourRepository;

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
}
