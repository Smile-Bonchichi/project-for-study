package kg.it.academy.OnlineAuction.controller;

import kg.it.academy.OnlineAuction.dto.refill.RefillRequestDto;
import kg.it.academy.OnlineAuction.dto.user.request.UserAuthDto;
import kg.it.academy.OnlineAuction.dto.user.request.UserRequestDto;
import kg.it.academy.OnlineAuction.dto.user.response.UserResponseDto;
import kg.it.academy.OnlineAuction.entity.User;
import kg.it.academy.OnlineAuction.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {
    final UserService userService;

    @PostMapping("/sign-in")
    public String getAuthToken(@RequestBody UserAuthDto userAuthDto) {
        return userService.getToken(userAuthDto);
    }

    @PostMapping("/save")
    public UserResponseDto save(@RequestBody UserRequestDto userRequestDto) {
        return userService.save(userRequestDto);
    }

    @PostMapping("/refill")
    public BigDecimal refillWallet(@RequestBody RefillRequestDto refillRequestDto) {
        return userService.payWallet(refillRequestDto);
    }

    @GetMapping("/get-all-user")
    public List<User> getAll() {
        return userService.getAllForAdmin();
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }
}
