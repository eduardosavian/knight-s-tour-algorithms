package xyz.computer_algorithms.repository;

import xyz.computer_algorithms.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
