package kg.it.academy.OnlineAuction.controller;

import kg.it.academy.OnlineAuction.dto.auction.request.AuctionRequestDto;
import kg.it.academy.OnlineAuction.dto.auction.response.AuctionResponseDto;
import kg.it.academy.OnlineAuction.service.AuctionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auction")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuctionController {
    final AuctionService auctionService;

    @PostMapping("/save")
    public AuctionResponseDto save(@RequestBody AuctionRequestDto auctionRequestDto) {
        return auctionService.save(auctionRequestDto);
    }

    @GetMapping("/get-all-auction")
    public List<AuctionResponseDto> getAll() {
        return auctionService.getAll();
    }

    @GetMapping("/search-auction/{name}")
    public List<AuctionResponseDto> getAllAuctionByName(@PathVariable String name) {
        return auctionService.getAllByName(name);
    }

    @GetMapping("/get-all-auction-active")
    public List<AuctionResponseDto> getAllAuctionByActive() {
        return auctionService.getAllByActive();
    }

    @GetMapping("/get-all-auction-advertising")
    public List<AuctionResponseDto> getAllAuctionByAdvertising() {
        return auctionService.getAllByAllByInAdvertising();
    }

    @GetMapping("/{id}")
    public AuctionResponseDto findById(@PathVariable Long id) {
        return auctionService.findById(id);
    }
}
