package xyz.computer_algorithms.repository;

import xyz.computer_algorithms.model.KnightsTour;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnightsTourRepository extends JpaRepository<KnightsTour, Long>{

}
