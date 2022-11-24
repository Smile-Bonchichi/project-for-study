package kg.it.academy.OnlineAuction.service;

import kg.it.academy.OnlineAuction.dto.place.bet.request.PlaceBetRequestDto;
import kg.it.academy.OnlineAuction.dto.place.bet.response.PlaceBetResponseDto;

public interface PlaceBetService {
    PlaceBetResponseDto placeBet(PlaceBetRequestDto placeBetRequestDto);
}
