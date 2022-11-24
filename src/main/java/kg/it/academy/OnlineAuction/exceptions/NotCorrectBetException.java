package kg.it.academy.OnlineAuction.exceptions;

import org.springframework.http.HttpStatus;

public class NotCorrectBetException extends BaseException {
    public NotCorrectBetException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
