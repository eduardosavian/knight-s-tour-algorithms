package xyz.computer_algorithms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import xyz.computer_algorithms.DTO.KnightsTourDTO;
import xyz.computer_algorithms.model.KnightsTour;
import xyz.computer_algorithms.service.KnightsTourService;

@RestController
@RequestMapping("api/knights-tour")
public class KnightsTourController {
    private final KnightsTourService knightsTourService;

    public KnightsTourController(KnightsTourService knightsTourService) {
        this.knightsTourService = knightsTourService;
    }

    @GetMapping
    public ResponseEntity<List<KnightsTour>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(knightsTourService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<KnightsTour>> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(knightsTourService.findById(id));
    }

    @PostMapping("/solve")
    public ResponseEntity<?> createAndSolveKnightsTour(@Valid @RequestBody KnightsTourDTO requestDTO) {
        try {
            int[][] solution = knightsTourService.createAndSolveKnightsTour(
                    requestDTO.getBoardSize(),
                    requestDTO.getStartX(),
                    requestDTO.getStartY(),
                    requestDTO.getBacktrackType()
            );

            if (solution == null) {
                return ((BodyBuilder) ResponseEntity.notFound()).body("No solution found.");
            }

            return ResponseEntity.ok(solution);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An internal error occurred.");
        }
    }

    @PostMapping("/s")
    public ResponseEntity<?> solveKnightsTour(@Valid @RequestBody KnightsTourDTO requestDTO) {
        try {
            int[][] solution = knightsTourService.solveKnightsTour(
                    requestDTO.getBoardSize(),
                    requestDTO.getStartX(),
                    requestDTO.getStartY(),
                    requestDTO.getBacktrackType()
            );
            if (solution == null) {
                return ((BodyBuilder) ResponseEntity.notFound()).body("No solution found.");
            }
            return ResponseEntity.ok(solution);
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
    public ResponseEntity<KnightsTour> update(@RequestBody KnightsTour knightsTour) {
        return ResponseEntity.status(HttpStatus.OK).body(knightsTourService.update(knightsTour));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        knightsTourService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
