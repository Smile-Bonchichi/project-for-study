package kg.it.academy.OnlineAuction.dto.auction.response;

import kg.it.academy.OnlineAuction.dto.Item.response.ItemResponseForAuctionDto;
import kg.it.academy.OnlineAuction.enums.Status;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuctionResponseDto {
    LocalDateTime startTime;

    LocalDateTime endTime;

    String name;

    ItemResponseForAuctionDto item;

    Status status;
}
