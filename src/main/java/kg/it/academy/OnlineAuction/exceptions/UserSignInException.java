package kg.it.academy.OnlineAuction.exceptions;

import org.springframework.http.HttpStatus;

public class UserSignInException extends BaseException {
    public UserSignInException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
