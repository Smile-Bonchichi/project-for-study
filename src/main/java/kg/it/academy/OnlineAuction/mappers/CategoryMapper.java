package kg.it.academy.OnlineAuction.mappers;

import kg.it.academy.OnlineAuction.dto.category.request.CategoryRequestDto;
import kg.it.academy.OnlineAuction.dto.category.response.CategoryResponseDto;
import kg.it.academy.OnlineAuction.entity.Category;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategoryEntity(CategoryRequestDto categoryRequestDto);

    Category toCategoryEntity(CategoryResponseDto categoryResponseDto);

    CategoryResponseDto toResponseDto(Category category);

    List<CategoryResponseDto> toCategoriesDto(List<Category> categories);
}
