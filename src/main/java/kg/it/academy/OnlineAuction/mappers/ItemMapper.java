package kg.it.academy.OnlineAuction.mappers;

import kg.it.academy.OnlineAuction.dto.Item.request.ItemRequestDto;
import kg.it.academy.OnlineAuction.dto.Item.response.ItemResponseDto;
import kg.it.academy.OnlineAuction.entity.Item;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    Item toItemEntity(ItemRequestDto itemRequestDto);

    @Mappings({
            @Mapping(target = "category", source = "category")
    })
    ItemResponseDto toItemResponseDto(Item item);

    List<ItemResponseDto> toItemsResponseDto(List<Item> items);
}
