package kg.it.academy.OnlineAuction.mappers;

import kg.it.academy.OnlineAuction.dto.role.request.RoleRequestDto;
import kg.it.academy.OnlineAuction.dto.role.response.RoleResponseDto;
import kg.it.academy.OnlineAuction.entity.Role;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role toRoleEntity(RoleRequestDto roleRequestDto);

    Role toRoleEntity(RoleResponseDto roleResponseDto);

    RoleResponseDto toResponseDto(Role category);

    List<RoleResponseDto> toRolesDto(List<Role> categories);
}
