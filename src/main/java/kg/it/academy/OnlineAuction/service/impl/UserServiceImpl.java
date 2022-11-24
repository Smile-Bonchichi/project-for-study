package kg.it.academy.OnlineAuction.service.impl;

import kg.it.academy.OnlineAuction.dto.refill.RefillForUserDto;
import kg.it.academy.OnlineAuction.dto.refill.RefillRequestDto;
import kg.it.academy.OnlineAuction.dto.user.request.UserAuthDto;
import kg.it.academy.OnlineAuction.dto.user.request.UserRequestDto;
import kg.it.academy.OnlineAuction.dto.user.response.UserResponseDto;
import kg.it.academy.OnlineAuction.entity.User;
import kg.it.academy.OnlineAuction.entity.UserRole;
import kg.it.academy.OnlineAuction.exceptions.MailSenderException;
import kg.it.academy.OnlineAuction.exceptions.NotUniqueRecord;
import kg.it.academy.OnlineAuction.exceptions.UserSignInException;
import kg.it.academy.OnlineAuction.mappers.RefillMapper;
import kg.it.academy.OnlineAuction.mappers.RoleMapper;
import kg.it.academy.OnlineAuction.mappers.UserMapper;
import kg.it.academy.OnlineAuction.repository.UserRepository;
import kg.it.academy.OnlineAuction.repository.UserRoleRepository;
import kg.it.academy.OnlineAuction.service.RefillService;
import kg.it.academy.OnlineAuction.service.RoleService;
import kg.it.academy.OnlineAuction.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final UserRoleRepository userRoleRepository;
    final RoleService roleService;

    final RefillService refillService;
    final PasswordEncoder passwordEncoder;

    @Override
    public String getToken(UserAuthDto userAuthDto) {
        User user = userRepository
                .findByLoginOrEmail(userAuthDto.getLoginOrEmail());
        boolean isMatches = passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword());
        if (isMatches) {
            return "Basic " + new String(Base64.getEncoder()
                    .encode((user.getLogin() + ":" + userAuthDto.getPassword()).getBytes()));
        } else {
            throw new UserSignInException("Неправильный логин или пароль!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public BigDecimal payWallet(RefillRequestDto refillRequestDto) {
        RefillForUserDto refillForUserDto = RefillMapper.INSTANCE.toRefillForUser(refillRequestDto);
        refillForUserDto.setUser(userRepository.findByLoginOrEmail(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        ));

        userRepository.updateWallet(
                refillForUserDto.getUser().getWallet().add(refillRequestDto.getAmount()),
                refillForUserDto.getUser().getId()
        );

        return refillService.payWallet(refillForUserDto);
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        try {
            if (userRequestDto.getEmail() == null)
                throw new MailSenderException("Нету почты!", HttpStatus.BAD_REQUEST);

            UserRole userRole = new UserRole();
            User user = UserMapper.INSTANCE.toUserEntity(userRequestDto);

            user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
            user.setWallet(BigDecimal.valueOf(0));
            user.setIsActive(1L);
            user.setEmail(userRequestDto.getEmail());

            userRole.setUser(userRepository.save(user));

            if (user.getLogin().equals("admin")) {
                userRole.setRole(RoleMapper.INSTANCE.toRoleEntity(roleService.findById(1L)));
            } else {
                userRole.setRole(RoleMapper.INSTANCE.toRoleEntity(roleService.findById(2L)));
            }

            userRoleRepository.save(userRole);

            return UserMapper.INSTANCE.toUserResponseDto(user);
        } catch (Exception ignored) {
            throw new NotUniqueRecord("Не уникальный логин", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<UserResponseDto> getAll() {
        return UserMapper.INSTANCE.toUsersResponseDto(userRepository.findAll());
    }

    @Override
    public List<User> getAllForAdmin() {
        return userRepository.findAll();
    }

    @Override
    public UserResponseDto findById(Long id) {
        return UserMapper.INSTANCE.toUserResponseDto(userRepository.getById(id));
    }
}
