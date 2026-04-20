package mate.academy.springbootbookstore.mapper;

import mate.academy.springbootbookstore.config.MapperConfig;
import mate.academy.springbootbookstore.dto.category.CategoryDto;
import mate.academy.springbootbookstore.dto.category.CreateCategoryRequestDto;
import mate.academy.springbootbookstore.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toModel(CreateCategoryRequestDto requestDto);

}
