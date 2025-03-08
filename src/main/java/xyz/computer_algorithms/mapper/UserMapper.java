package xyz.computer_algorithms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import xyz.computer_algorithms.dto.UserCreationDTO;
import xyz.computer_algorithms.dto.UserDTO;
import xyz.computer_algorithms.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User userCreationDTOToUser(UserCreationDTO userCreationDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    User userDTOToUser(UserDTO userDTO);

    UserDTO userToUserDTO(User user);
}