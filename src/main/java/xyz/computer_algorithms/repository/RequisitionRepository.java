package xyz.computer_algorithms.repository;

import xyz.computer_algorithms.model.Requisition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisitionRepository extends JpaRepository<Requisition, Long>{

}
