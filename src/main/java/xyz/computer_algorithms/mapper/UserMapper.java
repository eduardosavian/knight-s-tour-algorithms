package xyz.computer_algorithms.mapper;


import org.mapstruct.Mapper;

import xyz.computer_algorithms.dto.UserDTO;
import xyz.computer_algorithms.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDto(User user);

    User userDtoToUser(UserDTO userDto);
}