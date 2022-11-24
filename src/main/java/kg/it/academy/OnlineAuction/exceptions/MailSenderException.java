package kg.it.academy.OnlineAuction.exceptions;

import org.springframework.http.HttpStatus;

public class MailSenderException extends BaseException {
    public MailSenderException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
