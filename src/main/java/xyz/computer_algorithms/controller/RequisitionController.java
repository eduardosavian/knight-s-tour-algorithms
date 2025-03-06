package xyz.computer_algorithms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.computer_algorithms.model.Requisition;
import xyz.computer_algorithms.service.RequisitonService;

@RestController
@RequestMapping("api/requisition")
public class RequisitionController {
    private final RequisitonService requisitonService;

    public RequisitionController(RequisitonService requisitonService) {
        this.requisitonService = requisitonService;
    }

    @GetMapping
    public ResponseEntity<List<Requisition>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(requisitonService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Requisition>> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(requisitonService.findById(id));
    }


    @PostMapping("")
    public ResponseEntity<String> createRequisition(@RequestBody Requisition requisition) {
        try {
            return ResponseEntity.ok("Requisition created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An internal error occurred.");
        }
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<Requisition> update(@RequestBody Requisition requisition) {
        return ResponseEntity.status(HttpStatus.OK).body(requisitonService.update(requisition));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        requisitonService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
