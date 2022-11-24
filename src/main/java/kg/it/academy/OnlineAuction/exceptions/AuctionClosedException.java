package kg.it.academy.OnlineAuction.exceptions;

import org.springframework.http.HttpStatus;

public class AuctionClosedException extends BaseException {
    public AuctionClosedException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
