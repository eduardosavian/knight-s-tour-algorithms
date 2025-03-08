package xyz.computer_algorithms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import xyz.computer_algorithms.dto.UserCreationDTO;
import xyz.computer_algorithms.dto.UserDTO;
import xyz.computer_algorithms.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userCreationDTOToUser(UserCreationDTO userCreationDTO);

    User userDTOToUser(UserDTO userDTO);

    UserDTO userToUserDTO(User user);
}