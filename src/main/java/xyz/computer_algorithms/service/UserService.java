package xyz.computer_algorithms.service;

import xyz.computer_algorithms.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    User update(User user);

    void deleteById(Long id);
}