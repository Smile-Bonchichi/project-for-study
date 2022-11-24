package kg.it.academy.OnlineAuction.service.impl;

import kg.it.academy.OnlineAuction.dto.history.HistoryDto;
import kg.it.academy.OnlineAuction.entity.History;
import kg.it.academy.OnlineAuction.mappers.HistoryMapper;
import kg.it.academy.OnlineAuction.repository.HistoryRepository;
import kg.it.academy.OnlineAuction.service.HistoryService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoryServiceImpl implements HistoryService {
    final HistoryRepository historyRepository;

    @Override
    public HistoryDto save(History history) {
        return null;
    }

    @Override
    public History saveHistory(History history) {
        return historyRepository.save(history);
    }

    @Override
    public BigDecimal getMaxPrice(Long id) {
        return historyRepository.getMaxAuctionPrice(id);
    }

    @Override
    public List<HistoryDto> getAll() {
        return HistoryMapper.INSTANCE.toHistoriesResponseDto(historyRepository.findAll());
    }

    @Override
    public HistoryDto findById(Long id) {
        return HistoryMapper.INSTANCE.toHistoryDto(historyRepository.getById(id));
    }
}
