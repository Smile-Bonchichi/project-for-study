package kg.it.academy.OnlineAuction.util;

import kg.it.academy.OnlineAuction.exceptions.*;
import kg.it.academy.OnlineAuction.model.ResponseMessageModel;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    @ExceptionHandler(value = {
            UserNotFoundException.class,
            UserSignInException.class,
            UserNotHaveMoneyException.class,

            LowPriceException.class,
            NotCorrectBetException.class,
            OnlineBankException.class,

            NotUniqueRecord.class,
            NotUniqueItemOnAuction.class,

            AuctionClosedException.class,

            MailSenderException.class
    })
    public ResponseEntity<ResponseMessageModel<String>> handleFailException(BaseException baseException) {
        ResponseMessageModel<String> exceptionResponseMessageModel = new ResponseMessageModel<>();
        exceptionResponseMessageModel.setMessage(baseException.getMessage());
        String threwClassName = baseException.getStackTrace()[0].getClassName();
        log.warn(threwClassName + " : " + baseException.getMessage());
        return new ResponseEntity<>(exceptionResponseMessageModel, baseException.getHttpStatus());
    }
}
