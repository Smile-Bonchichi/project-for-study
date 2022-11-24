package kg.it.academy.OnlineAuction.dto.user.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuthDto {
    String loginOrEmail;

    String password;
}
