package xyz.computer_algorithms.service;

import xyz.computer_algorithms.dto.UserCreationDTO;
import xyz.computer_algorithms.mapper.UserMapper;
import xyz.computer_algorithms.model.User;
import xyz.computer_algorithms.repository.UserRepository;


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

    public User createUser(UserCreationDTO userCreationDTO) {
        User user = userMapper.userCreationDTOToUser(userCreationDTO);
        return userRepository.save(user);
    }

}