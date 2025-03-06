package xyz.computer_algorithms.controller;

import xyz.computer_algorithms.dto.UserDTO;
import xyz.computer_algorithms.model.User;
import xyz.computer_algorithms.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTOs = users.stream()
                .map(userService::getUserDTO)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            UserDTO userDTO = userService.getUserDTO(userOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.save(user);
            UserDTO createdUserDTO = userService.getUserDTO(createdUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // or a custom error DTO
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null); // or a custom error DTO
        }
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody User user) {
        User updatedUser = userService.update(user);
        UserDTO updatedUserDTO = userService.getUserDTO(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUserDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}