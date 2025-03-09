package xyz.computer_algorithms.dto;

import lombok.Data;


@Data
public class UserCreationDTO {
    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;
}
