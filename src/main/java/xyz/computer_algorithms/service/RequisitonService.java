package xyz.computer_algorithms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import xyz.computer_algorithms.model.Requisition;

@Service
public interface RequisitonService {
    Requisition save(Requisition requisition);

    List<Requisition> findAll();

    Optional<Requisition> findById(Long id);

    Requisition update(Requisition requisition);

    void deleteById(Long id);
}
