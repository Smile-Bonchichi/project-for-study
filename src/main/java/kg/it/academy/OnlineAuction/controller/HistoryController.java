package kg.it.academy.OnlineAuction.controller;

import kg.it.academy.OnlineAuction.dto.history.HistoryDto;
import kg.it.academy.OnlineAuction.service.HistoryService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoryController {
    final HistoryService historyService;

    @GetMapping("/get-all-history")
    public List<HistoryDto> getAll() {
        return historyService.getAll();
    }

    @GetMapping("/{id}")
    public HistoryDto findById(@PathVariable Long id) {
        return historyService.findById(id);
    }
}
