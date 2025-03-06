package xyz.computer_algorithms.service;

import xyz.computer_algorithms.dto.UserDTO;
import xyz.computer_algorithms.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public interface UserService {
    @Transactional
    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    User update(User user);

    void deleteById(Long id);

    public UserDTO getUserDTO(User user);
}