package kg.it.academy.OnlineAuction.service;

import kg.it.academy.OnlineAuction.dto.auction.request.AuctionRequestDto;
import kg.it.academy.OnlineAuction.dto.auction.response.AuctionResponseDto;

import java.util.List;

public interface AuctionService extends BaseService<AuctionResponseDto, AuctionRequestDto> {
    List<AuctionResponseDto> getAllByName(String name);

    List<AuctionResponseDto> getAllByAllByInAdvertising();

    List<AuctionResponseDto> getAllByActive();
}
