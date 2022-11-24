package kg.it.academy.OnlineAuction.service.impl;

import kg.it.academy.OnlineAuction.service.MailSenderService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MailSenderServiceImpl implements MailSenderService {
    final JavaMailSender javaMailSender;
    final Environment environment;

    @Override
    public boolean sendMail(String email, String auctionInfo) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            mimeMessage.setSubject("Вы выиграли в Аукционе!!!", "UTF-8");

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setFrom(Objects.requireNonNull(environment.getProperty("spring.mail.username")));
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText(auctionInfo, true);
            javaMailSender.send(mimeMessage);

            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}
