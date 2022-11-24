package kg.it.academy.OnlineAuction.service.impl;

import kg.it.academy.OnlineAuction.dto.auction.request.AuctionRequestDto;
import kg.it.academy.OnlineAuction.dto.auction.response.AuctionResponseDto;
import kg.it.academy.OnlineAuction.entity.Auction;
import kg.it.academy.OnlineAuction.enums.Status;
import kg.it.academy.OnlineAuction.exceptions.NotUniqueItemOnAuction;
import kg.it.academy.OnlineAuction.exceptions.NotUniqueRecord;
import kg.it.academy.OnlineAuction.mappers.AuctionMapper;
import kg.it.academy.OnlineAuction.repository.AuctionRepository;
import kg.it.academy.OnlineAuction.repository.ItemRepository;
import kg.it.academy.OnlineAuction.service.AuctionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuctionServiceImpl implements AuctionService {
    final AuctionRepository auctionRepository;
    final ItemRepository itemRepository;

    @Override
    public AuctionResponseDto save(AuctionRequestDto auctionRequestDto) {
        if (checkItem(auctionRequestDto.getItemId())) {
            Auction auction = AuctionMapper.INSTANCE.toAuctionEntity(auctionRequestDto);
            auction.setItem(itemRepository.getItemById(auctionRequestDto.getItemId()));
            if (LocalDateTime.now().compareTo(auctionRequestDto.getStartTime()) <= 0)
                auction.setStatus(Status.IN_ADVERTISING);
            else auction.setStatus(Status.ACTIVE);
            try {
                return AuctionMapper.INSTANCE.toAuctionDto(auctionRepository.save(auction));
            } catch (Exception ignored) {
                throw new NotUniqueRecord("Одинноковое название аукциона", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new NotUniqueItemOnAuction("Эта вещь уже на аукционе", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<AuctionResponseDto> getAll() {
        return AuctionMapper.INSTANCE.toAuctionsDto(auctionRepository.findAll());
    }

    @Override
    public AuctionResponseDto findById(Long id) {
        return AuctionMapper.INSTANCE.toAuctionDto(auctionRepository.getById(id));
    }

    @Override
    public List<AuctionResponseDto> getAllByName(String name) {
        return AuctionMapper.INSTANCE.toAuctionsDto(auctionRepository.getAllByName(name));
    }

    @Override
    public List<AuctionResponseDto> getAllByAllByInAdvertising() {
        return AuctionMapper.INSTANCE.toAuctionsDto(auctionRepository.getAllByInAdvertising());
    }

    @Override
    public List<AuctionResponseDto> getAllByActive() {
        return AuctionMapper.INSTANCE.toAuctionsDto(auctionRepository.getAllByActive());
    }

    private boolean checkItem(Long id) {
        return auctionRepository.checkUniqueItemOnAuction(id) == 0;
    }
}
