package kg.it.academy.OnlineAuction.boot;

import kg.it.academy.OnlineAuction.dto.category.request.CategoryRequestDto;
import kg.it.academy.OnlineAuction.dto.user.request.UserRequestDto;
import kg.it.academy.OnlineAuction.entity.Role;
import kg.it.academy.OnlineAuction.repository.RoleRepository;
import kg.it.academy.OnlineAuction.repository.UserRepository;
import kg.it.academy.OnlineAuction.service.CategoryService;

import kg.it.academy.OnlineAuction.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationStartRunner implements CommandLineRunner {
    final RoleRepository roleRepository;
    final CategoryService categoryService;
    final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(Role.builder()
                .nameRole("ROLE_ADMIN")
                .build());
        roleRepository.save(Role.builder()
                .nameRole("ROLE_USER")
                .build());

        categoryService.save(CategoryRequestDto.builder()
                .name("Одежда")
                .build());
        categoryService.save(CategoryRequestDto.builder()
                .name("Посуда")
                .build());
        categoryService.save(CategoryRequestDto.builder()
                .name("Электроника")
                .build());
        categoryService.save(CategoryRequestDto.builder()
                .name("Барахло")
                .build());

        userService.save(UserRequestDto.builder()
                .login("nurdin")
                .password("123")
                .email("ulanovnurdin@gmail.com")
                .build());

        userService.save(UserRequestDto.builder()
                .login("ruslan")
                .password("123")
                .email("ruslan@gmail.com")
                .build());

        userService.save(UserRequestDto.builder()
                .login("bermet")
                .password("123")
                .email("bermet@gmail.com")
                .build());

        userService.save(UserRequestDto.builder()
                .login("admin")
                .password("123")
                .email("admin@gmail.com")
                .build());
    }
}
