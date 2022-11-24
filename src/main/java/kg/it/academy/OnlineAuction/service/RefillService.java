package kg.it.academy.OnlineAuction.service;

import kg.it.academy.OnlineAuction.dto.refill.RefillForUserDto;

import java.math.BigDecimal;

public interface RefillService {
    BigDecimal payWallet(RefillForUserDto refillForUserDto);
}
