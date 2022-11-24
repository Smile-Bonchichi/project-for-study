package kg.it.academy.OnlineAuction.service;

import kg.it.academy.OnlineAuction.dto.refill.RefillRequestDto;
import kg.it.academy.OnlineAuction.dto.user.request.UserAuthDto;
import kg.it.academy.OnlineAuction.dto.user.request.UserRequestDto;
import kg.it.academy.OnlineAuction.dto.user.response.UserResponseDto;
import kg.it.academy.OnlineAuction.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService extends BaseService<UserResponseDto, UserRequestDto> {
    String getToken(UserAuthDto userAuthDto);

    BigDecimal payWallet(RefillRequestDto refillRequestDto);

    List<User> getAllForAdmin();
}
