package kg.it.academy.OnlineAuction.service.impl;

import kg.it.academy.OnlineAuction.dto.place.bet.request.PlaceBetRequestDto;
import kg.it.academy.OnlineAuction.dto.place.bet.response.PlaceBetResponseDto;
import kg.it.academy.OnlineAuction.entity.Auction;
import kg.it.academy.OnlineAuction.entity.History;
import kg.it.academy.OnlineAuction.entity.Item;
import kg.it.academy.OnlineAuction.entity.User;
import kg.it.academy.OnlineAuction.exceptions.AuctionClosedException;
import kg.it.academy.OnlineAuction.exceptions.LowPriceException;
import kg.it.academy.OnlineAuction.exceptions.NotCorrectBetException;
import kg.it.academy.OnlineAuction.exceptions.UserNotHaveMoneyException;
import kg.it.academy.OnlineAuction.repository.AuctionRepository;
import kg.it.academy.OnlineAuction.repository.ItemRepository;
import kg.it.academy.OnlineAuction.repository.UserRepository;
import kg.it.academy.OnlineAuction.service.HistoryService;
import kg.it.academy.OnlineAuction.service.PlaceBetService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaceBetServiceImpl implements PlaceBetService {
    final AuctionRepository auctionRepository;
    final HistoryService historyService;
    final UserRepository userRepository;
    final ItemRepository itemRepository;

    @Override
    public PlaceBetResponseDto placeBet(PlaceBetRequestDto placeBetRequestDto) {
        if (auctionRepository.getIsActiveAuction(placeBetRequestDto.getAuctionId())) {
            if (auctionRepository.getAuctionById(placeBetRequestDto.getAuctionId()).
                    getStartPrice().compareTo(placeBetRequestDto.getPrice()) < 0) {
                if (placeBetRequestDto.getPrice().compareTo(
                        historyService.getMaxPrice(placeBetRequestDto.getAuctionId())) > 0) {

                    User user = userRepository.findByLoginOrEmail(
                            SecurityContextHolder
                                    .getContext()
                                    .getAuthentication()
                                    .getName());

                    if (user.getWallet().compareTo(placeBetRequestDto.getPrice()) < 0) {
                        throw new UserNotHaveMoneyException("Нету денег на балансе", HttpStatus.PAYMENT_REQUIRED);
                    }

                    Auction auction = auctionRepository
                            .getAuctionById(placeBetRequestDto.getAuctionId());
                    Item item = itemRepository.getItemById(auction.getItem().getId());

                    if (user.getId().equals(item.getUser().getId())) {
                        throw new NotCorrectBetException("Нельзя на свою же вещь сделать ставку", HttpStatus.PAYMENT_REQUIRED);
                    }

                    History history = historyService.saveHistory(History.builder()
                            .auction(auction)
                            .user(user)
                            .price(placeBetRequestDto.getPrice())
                            .build());

                    return PlaceBetResponseDto.builder()
                            .auctionName(history.getAuction().getName())
                            .price(history.getPrice())
                            .build();
                } else {
                    throw new LowPriceException("Маленькая цена", HttpStatus.PAYMENT_REQUIRED);
                }
            } else {
                throw new LowPriceException("Цена меньше начальной!", HttpStatus.PAYMENT_REQUIRED);
            }
        } else {
            throw new AuctionClosedException("Аукцион закрыт или еще не начался", HttpStatus.BAD_REQUEST);
        }
    }
}
