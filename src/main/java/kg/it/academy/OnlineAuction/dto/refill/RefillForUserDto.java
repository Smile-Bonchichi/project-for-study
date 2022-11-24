package kg.it.academy.OnlineAuction.dto.refill;

import kg.it.academy.OnlineAuction.entity.User;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefillForUserDto {
    String cardNumber;

    BigDecimal amount;

    User user;
}
