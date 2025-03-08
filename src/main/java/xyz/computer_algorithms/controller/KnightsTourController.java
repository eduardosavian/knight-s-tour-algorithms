package xyz.computer_algorithms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.computer_algorithms.model.KnightsTour;
import xyz.computer_algorithms.service.KnightsTourService;

@RestController
@RequestMapping("api/knights-tour")
public class KnightsTourController {
    private final KnightsTourService knightsTourService;

    public KnightsTourController(KnightsTourService knightsTourService) {
        this.knightsTourService = knightsTourService;
    }

    @PostMapping("/solve")
    public ResponseEntity<KnightsTour> createUser(@RequestBody KnightsTour knightsTour) {
        try {
            KnightsTour createdKnightsTour = knightsTourService.save(knightsTour);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdKnightsTour);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
