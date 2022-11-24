package kg.it.academy.OnlineAuction.mappers;

import kg.it.academy.OnlineAuction.dto.user.request.UserAuthDto;
import kg.it.academy.OnlineAuction.dto.user.request.UserRequestDto;
import kg.it.academy.OnlineAuction.dto.user.response.UserResponseDto;
import kg.it.academy.OnlineAuction.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUserEntity(UserRequestDto userRequestDto);

    UserResponseDto toUserResponseDto(User user);

    List<UserResponseDto> toUsersResponseDto(List<User> users);

    UserAuthDto toUserAuthDto(User user);
}
