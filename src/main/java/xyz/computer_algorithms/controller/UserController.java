package xyz.computer_algorithms.controller;

import java.util.List;
import java.util.stream.Collectors;

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
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreationDTO userCreationDTO) {
        User user = userMapper.userCreationDTOToUser(userCreationDTO);
        User createdUser = userService.save(user);
        UserDTO userDTO = userMapper.userToUserDTO(createdUser);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        if (ex.getMessage().contains("uk6dotkott2kjsp8vw4d0m25fb7")) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email address already in use.");
        } else if (ex.getMessage().contains("ukr43af9ap4edm43mmtq01oddj6")) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Username already in use.");
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

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTOs = users.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

}