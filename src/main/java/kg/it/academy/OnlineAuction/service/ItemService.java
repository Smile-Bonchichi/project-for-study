package kg.it.academy.OnlineAuction.service;

import kg.it.academy.OnlineAuction.dto.Item.request.ItemRequestDto;
import kg.it.academy.OnlineAuction.dto.Item.response.ItemResponseDto;

import java.util.List;

public interface ItemService extends BaseService<ItemResponseDto, ItemRequestDto> {
    List<ItemResponseDto> getMyItem();
}
