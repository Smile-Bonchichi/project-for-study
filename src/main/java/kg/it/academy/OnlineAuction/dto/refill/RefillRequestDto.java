package kg.it.academy.OnlineAuction.dto.refill;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefillRequestDto {
    String cardNumber;

    BigDecimal amount;
}
