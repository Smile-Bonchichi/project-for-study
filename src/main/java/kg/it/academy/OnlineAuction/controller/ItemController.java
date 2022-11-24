package kg.it.academy.OnlineAuction.controller;

import kg.it.academy.OnlineAuction.dto.Item.request.ItemRequestDto;
import kg.it.academy.OnlineAuction.dto.Item.response.ItemResponseDto;
import kg.it.academy.OnlineAuction.service.ItemService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemController {
    final ItemService itemService;

    @PostMapping
    public ItemResponseDto save(@RequestBody ItemRequestDto itemRequestDto) {
        return itemService.save(itemRequestDto);
    }

    @GetMapping("/get-my-item")
    public List<ItemResponseDto> getMyItem() {
        return itemService.getMyItem();
    }

    @GetMapping("/get-all-item")
    public List<ItemResponseDto> getAll() {
        return itemService.getAll();
    }

    @GetMapping("/{id}")
    public ItemResponseDto findById(@PathVariable Long id) {
        return itemService.findById(id);
    }
}
