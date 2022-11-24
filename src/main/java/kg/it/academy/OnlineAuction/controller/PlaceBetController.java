package kg.it.academy.OnlineAuction.controller;

import kg.it.academy.OnlineAuction.dto.place.bet.request.PlaceBetRequestDto;
import kg.it.academy.OnlineAuction.dto.place.bet.response.PlaceBetResponseDto;
import kg.it.academy.OnlineAuction.service.PlaceBetService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/place-bet")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaceBetController {
    final PlaceBetService placeBetService;

    @PostMapping
    public PlaceBetResponseDto placeBet(@RequestBody PlaceBetRequestDto placeBetRequestDto) {
        return placeBetService.placeBet(placeBetRequestDto);
    }
}
