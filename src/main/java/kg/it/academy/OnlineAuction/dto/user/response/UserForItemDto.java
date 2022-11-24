package kg.it.academy.OnlineAuction.dto.user.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserForItemDto {
    Long id;

    String login;

    String email;
}
