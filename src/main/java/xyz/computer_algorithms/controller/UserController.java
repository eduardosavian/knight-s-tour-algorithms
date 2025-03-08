package xyz.computer_algorithms.controller;

import xyz.computer_algorithms.dto.UserCreationDTO;
import xyz.computer_algorithms.dto.UserDTO;
import xyz.computer_algorithms.mapper.UserMapper;
import xyz.computer_algorithms.model.User;
import xyz.computer_algorithms.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService) {
        this.userService = userService;
        this.userMapper = null;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreationDTO userCreationDTO) {
        try {
            User user = userMapper.userCreationDTOToUser(userCreationDTO); // Correct mapping
            User createdUser = userService.save(user);

            // Map the created User to UserDTO for the response
            UserDTO userDTO = userMapper.userToUserDTO(createdUser); // You need to add this method to the mapper
            return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);

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