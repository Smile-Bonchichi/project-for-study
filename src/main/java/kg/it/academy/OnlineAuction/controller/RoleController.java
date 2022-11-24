package kg.it.academy.OnlineAuction.controller;

import kg.it.academy.OnlineAuction.dto.role.request.RoleRequestDto;
import kg.it.academy.OnlineAuction.dto.role.response.RoleResponseDto;
import kg.it.academy.OnlineAuction.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleController {
    final RoleService roleService;

    @PostMapping
    public RoleResponseDto save(@RequestBody RoleRequestDto roleRequestDto) {
        return roleService.save(roleRequestDto);
    }

    @GetMapping
    public List<RoleResponseDto> getAll() {
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    public RoleResponseDto findById(@PathVariable Long id) {
        return roleService.findById(id);
    }
}
