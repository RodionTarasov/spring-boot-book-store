package mate.academy.springbootbookstore.service;

import mate.academy.springbootbookstore.dto.category.CategoryDto;
import mate.academy.springbootbookstore.dto.category.CreateCategoryRequestDto;
import mate.academy.springbootbookstore.exception.EntityNotFoundException;
import mate.academy.springbootbookstore.mapper.CategoryMapper;
import mate.academy.springbootbookstore.model.Category;
import mate.academy.springbootbookstore.repository.category.CategoryRepository;
import mate.academy.springbootbookstore.service.category.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @InjectMocks
    private CategoryServiceImpl categoryService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;


    @Test
    void save_ValidRequest_ReturnsCategoryDto() {
        CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto(
                "Fantasy",
                "Fantasy books"
        );
        Category category = new Category();
        category.setId(1L);
        category.setName("Fantasy");
        category.setDescription("Fantasy books");
        CategoryDto expected = new CategoryDto(
                1L,
                "Fantasy",
                "Fantasy books"
        );

        when(categoryMapper.toModel(requestDto)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(expected);
        CategoryDto actual = categoryService.save(requestDto);
        assertThat(actual).isEqualTo(expected);

        verify(categoryMapper).toModel(requestDto);
        verify(categoryRepository).save(category);
        verify(categoryMapper).toDto(category);
    }

    @Test
    void getById_ExistingId_ReturnsCategoryDto() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Fantasy");
        category.setDescription("Fantasy books");
        CategoryDto expected = new CategoryDto(
                1L,
                "Fantasy",
                "Fantasy books"
        );

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(expected);
        CategoryDto actual = categoryService.getById(1L);
        assertThat(actual).isEqualTo(expected);

        verify(categoryRepository).findById(1L);
        verify(categoryMapper).toDto(category);
    }

    @Test
    void getById_NonExistingId_ThrowsException() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> categoryService.getById(1L));

        verify(categoryRepository).findById(1L);
        verifyNoInteractions(categoryMapper);
    }

    @Test
    void deleteById_ValidId_CallsRepository() {
        categoryService.deleteById(1L);
        verify(categoryRepository).deleteById(1L);
    }

    @Test
    void findAll_ValidPageable_ReturnsPageOfCategoryDto() {
        Pageable pageable = PageRequest.of(0, 10);
        Category category = new Category();
        category.setId(1L);
        category.setName("Fantasy");
        category.setDescription("Fantasy books");
        CategoryDto categoryDto = new CategoryDto(
                1L,
                "Fantasy",
                "Fantasy books"
        );

        Page<Category> categoriesPage = new PageImpl<>(List.of(category));

        when(categoryRepository.findAll(pageable)).thenReturn(categoriesPage);
        when(categoryMapper.toDto(category)).thenReturn(categoryDto);
        Page<CategoryDto> actual = categoryService.findAll(pageable);
        assertThat(actual.getTotalElements()).isEqualTo(1);
        assertThat(actual.getContent().get(0)).isEqualTo(categoryDto);

        verify(categoryRepository).findAll(pageable);
        verify(categoryMapper).toDto(category);
    }
}
