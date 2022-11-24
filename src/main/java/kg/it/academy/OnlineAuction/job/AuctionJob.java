package kg.it.academy.OnlineAuction.job;

import kg.it.academy.OnlineAuction.entity.Auction;
import kg.it.academy.OnlineAuction.entity.User;
import kg.it.academy.OnlineAuction.enums.Status;
import kg.it.academy.OnlineAuction.exceptions.MailSenderException;
import kg.it.academy.OnlineAuction.repository.AuctionRepository;
import kg.it.academy.OnlineAuction.repository.HistoryRepository;
import kg.it.academy.OnlineAuction.repository.ItemRepository;
import kg.it.academy.OnlineAuction.repository.UserRepository;
import kg.it.academy.OnlineAuction.service.MailSenderService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuctionJob {
    final AuctionRepository auctionRepository;
    final MailSenderService mailSenderService;
    final HistoryRepository historyRepository;
    final UserRepository userRepository;
    final ItemRepository itemRepository;

    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void checkAuctionStatus() {
        List<Auction> auctions = auctionRepository.getAuctionByActiveAndAdvertising();

        auctions.forEach(x -> {
            if (x.getStartTime().compareTo(LocalDateTime.now()) <= 0 && x.getEndTime().compareTo(LocalDateTime.now()) >= 0) {
                auctionRepository.updateStatus(Status.ACTIVE.toString(), x.getId());
            } else if ((x.getEndTime().compareTo(LocalDateTime.now()) <= 0) && (x.getStatus().equals(Status.ACTIVE))) {
                auctionRepository.updateStatus(Status.NOT_ACTIVE.toString(), x.getId());

                User userWinner = userRepository.getById(historyRepository.getWinnerOnAuction(x.getId()));
                User userSeller = userRepository.getById(x.getItem().getUser().getId());
                BigDecimal maxAmount = historyRepository.getMaxAuctionPrice(x.getId());

                userRepository.updateWallet(maxAmount.add(userSeller.getWallet()), userSeller.getId());
                itemRepository.updateUserIdOnItem(userWinner.getId(), x.getItem().getId());
                userRepository.updateWallet(
                        userWinner.getWallet().subtract(historyRepository.getMaxAuctionPrice(x.getId())), userWinner.getId()
                );
                sendMail(x);
            }
        });
    }

    public void sendMail(Auction auction) {
        User user = userRepository.getById(historyRepository.getWinnerOnAuction(auction.getId()));

        userRepository.updateWallet(
                user.getWallet().subtract(historyRepository.getMaxAuctionPrice(auction.getId())), user.getId()
        );
        StringBuilder message = new StringBuilder().append("Вы выиграли на аукционе!!!\n")
                .append("Название: ").append(auction.getName())
                .append("\nЦена: ").append(historyRepository.getMaxAuctionPrice(auction.getId()));

        if (!mailSenderService.sendMail(user.getEmail(), message.toString())) {
            throw new MailSenderException("Что то пошло не так с отправкой сообщения...", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
