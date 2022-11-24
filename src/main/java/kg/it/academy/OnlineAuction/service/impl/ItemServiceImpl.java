package kg.it.academy.OnlineAuction.service.impl;

import kg.it.academy.OnlineAuction.dto.Item.request.ItemRequestDto;
import kg.it.academy.OnlineAuction.dto.Item.response.ItemResponseDto;
import kg.it.academy.OnlineAuction.entity.Category;
import kg.it.academy.OnlineAuction.entity.Item;
import kg.it.academy.OnlineAuction.exceptions.NotUniqueRecord;
import kg.it.academy.OnlineAuction.mappers.CategoryMapper;
import kg.it.academy.OnlineAuction.mappers.ItemMapper;
import kg.it.academy.OnlineAuction.repository.ItemRepository;
import kg.it.academy.OnlineAuction.repository.UserRepository;
import kg.it.academy.OnlineAuction.service.CategoryService;
import kg.it.academy.OnlineAuction.service.ItemService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemServiceImpl implements ItemService {
    final ItemRepository itemRepository;
    final UserRepository userRepository;
    final CategoryService categoryService;

    @Override
    public ItemResponseDto save(ItemRequestDto itemRequestDto) {
        try {
            Item item = ItemMapper.INSTANCE.toItemEntity(itemRequestDto);
            item.setUser(userRepository
                    .findByLoginOrEmail(SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getName())
            );

            List<Category> categories = new ArrayList<>();
            for (int i = 0; i < itemRequestDto.getCategoryId().size(); i++) {
                categories.add(CategoryMapper.INSTANCE
                        .toCategoryEntity(categoryService
                                .findById(itemRequestDto.getCategoryId().get(i))));
            }

            item.setCategory(categories);
            return ItemMapper.INSTANCE.toItemResponseDto(itemRepository.save(item));
        } catch (Exception ignored) {
            throw new NotUniqueRecord("Одинноковое название вещи", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ItemResponseDto> getMyItem() {
        return ItemMapper.INSTANCE.toItemsResponseDto(itemRepository
                .getMyItem(userRepository.findByLoginOrEmail(SecurityContextHolder.getContext()
                        .getAuthentication().getName()).getId()));
    }

    @Override
    public List<ItemResponseDto> getAll() {
        return ItemMapper.INSTANCE.toItemsResponseDto(itemRepository.findAll());
    }

    @Override
    public ItemResponseDto findById(Long id) {
        return ItemMapper.INSTANCE.toItemResponseDto(itemRepository.getById(id));
    }
}
