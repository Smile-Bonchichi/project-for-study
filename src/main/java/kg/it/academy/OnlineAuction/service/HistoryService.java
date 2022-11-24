package kg.it.academy.OnlineAuction.service;

import kg.it.academy.OnlineAuction.dto.history.HistoryDto;
import kg.it.academy.OnlineAuction.entity.History;

import java.math.BigDecimal;

public interface HistoryService extends BaseService<HistoryDto, History> {
    History saveHistory(History history);

    BigDecimal getMaxPrice(Long id);
}
