package kg.it.academy.OnlineAuction.service.impl;

import kg.it.academy.OnlineAuction.dto.category.request.CategoryRequestDto;
import kg.it.academy.OnlineAuction.dto.category.response.CategoryResponseDto;
import kg.it.academy.OnlineAuction.exceptions.NotUniqueRecord;
import kg.it.academy.OnlineAuction.mappers.CategoryMapper;
import kg.it.academy.OnlineAuction.repository.CategoryRepository;
import kg.it.academy.OnlineAuction.service.CategoryService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {
    final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto) {
        try {
            return CategoryMapper.INSTANCE
                    .toResponseDto(categoryRepository.save(
                            CategoryMapper.INSTANCE.toCategoryEntity(categoryRequestDto)));
        } catch (Exception ignored) {
            throw new NotUniqueRecord("Одинноковая категория", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<CategoryResponseDto> getAll() {
        return CategoryMapper.INSTANCE.toCategoriesDto(categoryRepository.findAll());
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        return CategoryMapper.INSTANCE.toResponseDto(categoryRepository.getById(id));
    }
}
