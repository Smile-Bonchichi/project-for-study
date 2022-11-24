package kg.it.academy.OnlineAuction.service.impl;

import kg.it.academy.OnlineAuction.dto.refill.RefillForUserDto;
import kg.it.academy.OnlineAuction.exceptions.OnlineBankException;
import kg.it.academy.OnlineAuction.service.RefillService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefillServiceImpl implements RefillService {
    final String urlCheckAmount = "https://online-bank-it-academy.herokuapp.com/api/card/check-amount/";
    final String urlRefill = "https://online-bank-it-academy.herokuapp.com/api/card/cash-withdrawal";

    @Override
    public BigDecimal payWallet(RefillForUserDto refillForUserDto) {
        if (checkAmountOnCard(refillForUserDto.getCardNumber(), refillForUserDto.getAmount())) {
            return setAmountOnWallet(refillForUserDto.getCardNumber(), refillForUserDto.getAmount());
        } else {
            throw new OnlineBankException("Недостаточно средств в онлайн кошельке", HttpStatus.PAYMENT_REQUIRED);
        }
    }

    private boolean checkAmountOnCard(String cardNumber, BigDecimal amount) {
        try {
            String responseString = EntityUtils.toString(
                    HttpClients.createDefault().execute(
                            new HttpGet(urlCheckAmount + cardNumber)
                    ).getEntity()
            );

            BigDecimal tempAmount = new BigDecimal(responseString.substring(
                    responseString.indexOf("unt") + 5, responseString.indexOf("curr") - 2
            ));
            return tempAmount.compareTo(amount) >= 0;

        } catch (Exception ignored) {
            throw new OnlineBankException("Что-то пошло не так с онлайн банкингом!", HttpStatus.CONFLICT);
        }
    }

    private BigDecimal setAmountOnWallet(String cardNumber, BigDecimal amount) {
        try {
            HttpPost httpPost = new HttpPost(urlRefill);
            httpPost.setEntity(new StringEntity("{ \"cardNumber\" : \"" + cardNumber + "\", \"amount\" : " + amount + " }"));
            httpPost.setHeader("Content-type", "application/json");

            String responseString = EntityUtils.toString(HttpClients.createDefault().execute(httpPost).getEntity());

            return new BigDecimal(
                    responseString.substring(responseString.indexOf("unt") + 5, responseString.length() - 1)
            );
        } catch (Exception ignored) {
            throw new OnlineBankException("Что-то пошло не так с онлайн банкингом!", HttpStatus.CONFLICT);
        }
    }
}
