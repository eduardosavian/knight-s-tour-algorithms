package xyz.computer_algorithms.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.computer_algorithms.dto.UserCreationDTO;
import xyz.computer_algorithms.dto.UserDTO;
import xyz.computer_algorithms.mapper.UserMapper;
import xyz.computer_algorithms.model.User;
import xyz.computer_algorithms.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserCreationDTO userCreationDTO) {
        User user = userMapper.userCreationDTOToUser(userCreationDTO);
        User createdUser = userService.save(user);
        return userMapper.userToUserDTO(createdUser);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        if (ex.getMessage().contains("uk6dotkott2kjsp8vw4d0m25fb7")) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email address already in use.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected database error occurred.");
        }
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}