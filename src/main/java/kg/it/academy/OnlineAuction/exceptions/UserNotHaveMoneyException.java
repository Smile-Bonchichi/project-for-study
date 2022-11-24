package kg.it.academy.OnlineAuction.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotHaveMoneyException extends BaseException {
    public UserNotHaveMoneyException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
