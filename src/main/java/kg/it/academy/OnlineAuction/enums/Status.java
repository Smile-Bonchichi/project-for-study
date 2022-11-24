package kg.it.academy.OnlineAuction.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Status {
    ACTIVE(0L, "Активен"),
    NOT_ACTIVE(0L, "Не активен"),
    IN_ADVERTISING(2L, "В рекламе");

    final Long id;
    final String message;
}
