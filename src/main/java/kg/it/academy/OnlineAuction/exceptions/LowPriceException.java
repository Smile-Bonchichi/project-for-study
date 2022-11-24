package kg.it.academy.OnlineAuction.exceptions;

import org.springframework.http.HttpStatus;

public class LowPriceException extends BaseException {
    public LowPriceException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
