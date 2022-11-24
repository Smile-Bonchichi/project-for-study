package kg.it.academy.OnlineAuction.exceptions;

import org.springframework.http.HttpStatus;

public class OnlineBankException extends BaseException {
    public OnlineBankException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
