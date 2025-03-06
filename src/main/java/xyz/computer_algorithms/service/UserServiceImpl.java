package xyz.computer_algorithms.service;

import xyz.computer_algorithms.dto.UserDTO;
import xyz.computer_algorithms.model.User;
import xyz.computer_algorithms.repository.UserRepository;
import xyz.computer_algorithms.mapper.UserMapper;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getUserDTO(User user) {
        return userMapper.userToUserDto(user);
    }
}