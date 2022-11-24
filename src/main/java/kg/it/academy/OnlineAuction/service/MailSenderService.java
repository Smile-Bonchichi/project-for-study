package kg.it.academy.OnlineAuction.service;

public interface MailSenderService {
    boolean sendMail(String email, String auctionInfo);
}
