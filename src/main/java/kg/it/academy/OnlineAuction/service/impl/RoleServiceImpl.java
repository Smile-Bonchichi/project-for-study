package kg.it.academy.OnlineAuction.service.impl;

import kg.it.academy.OnlineAuction.dto.role.request.RoleRequestDto;
import kg.it.academy.OnlineAuction.dto.role.response.RoleResponseDto;
import kg.it.academy.OnlineAuction.exceptions.NotUniqueRecord;
import kg.it.academy.OnlineAuction.mappers.RoleMapper;
import kg.it.academy.OnlineAuction.repository.RoleRepository;
import kg.it.academy.OnlineAuction.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleServiceImpl implements RoleService {
    final RoleRepository roleRepository;

    @Override
    public RoleResponseDto save(RoleRequestDto roleRequestDto) {
        try {
            roleRequestDto.setName("ROLE_" + roleRequestDto.getName().toUpperCase());
            System.out.println(roleRequestDto.getName());
            return RoleMapper.INSTANCE
                    .toResponseDto(roleRepository.save(RoleMapper.INSTANCE.toRoleEntity(roleRequestDto)));
        } catch (Exception ignored) {
            throw new NotUniqueRecord("Одинноковая роль", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<RoleResponseDto> getAll() {
        return RoleMapper.INSTANCE.toRolesDto(roleRepository.findAll());
    }

    @Override
    public RoleResponseDto findById(Long id) {
        return RoleMapper.INSTANCE.toResponseDto(roleRepository.getById(id));
    }
}
