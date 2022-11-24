package kg.it.academy.OnlineAuction.exceptions;

import org.springframework.http.HttpStatus;

public class NotUniqueItemOnAuction extends BaseException {
    public NotUniqueItemOnAuction(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
