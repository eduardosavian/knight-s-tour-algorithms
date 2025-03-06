package xyz.computer_algorithms.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 1000, message = "Username must be between 3 and 1000 characters")
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Size(min = 3, max = 355, message = "Username must be between 3 and 255 characters")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 256, message = "Password must be between 16 and 256 characters")
    private String password;

    @Size(min = 1, max = 1000, message = "First name must be between 1 and 1000 characters")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @Size(min = 1, max = 1000, message = "Last name must be between 3 and 1000 characters")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    private LocalDateTime createdAt;
}