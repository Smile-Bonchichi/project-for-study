package kg.it.academy.OnlineAuction.mappers;

import kg.it.academy.OnlineAuction.dto.history.HistoryDto;
import kg.it.academy.OnlineAuction.entity.History;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HistoryMapper {
    HistoryMapper INSTANCE = Mappers.getMapper(HistoryMapper.class);

    History toHistoryEntity(HistoryDto historyDto);

    HistoryDto toHistoryDto(History history);

    List<HistoryDto> toHistoriesResponseDto(List<History> items);
}
