package xyz.computer_algorithms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.computer_algorithms.model.Requisition;
import xyz.computer_algorithms.repository.RequisitionRepository;

@Service
public class RequisitionServiceImpl implements RequisitonService{
    @Autowired
    private RequisitionRepository requisitionRepository;

    @Override
    public Requisition save(Requisition requisition) {
        return requisitionRepository.save(requisition);
    }

    @Override
    public List<Requisition> findAll() {
        return requisitionRepository.findAll();
    }

    @Override
    public Optional<Requisition> findById(Long id) {
        return requisitionRepository.findById(id);
    }

    @Override
    public Requisition update(Requisition requisition) {
        return requisitionRepository.save(requisition);
    }

    @Override
    public void deleteById(Long id) {
        requisitionRepository.deleteById(id);
    }
}
