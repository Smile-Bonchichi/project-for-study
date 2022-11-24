package kg.it.academy.OnlineAuction.mappers;

import kg.it.academy.OnlineAuction.dto.auction.request.AuctionRequestDto;
import kg.it.academy.OnlineAuction.dto.auction.response.AuctionResponseDto;
import kg.it.academy.OnlineAuction.entity.Auction;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuctionMapper {
    AuctionMapper INSTANCE = Mappers.getMapper(AuctionMapper.class);

    Auction toAuctionEntity(AuctionRequestDto auctionRequestDto);

    AuctionResponseDto toAuctionDto(Auction auction);

    List<AuctionResponseDto> toAuctionsDto(List<Auction> auctions);
}
