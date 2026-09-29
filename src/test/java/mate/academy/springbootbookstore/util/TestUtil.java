package mate.academy.springbootbookstore.util;

import mate.academy.springbootbookstore.dto.book.BookDto;
import mate.academy.springbootbookstore.dto.book.CreateBookRequestDto;
import mate.academy.springbootbookstore.dto.category.CategoryDto;
import java.math.BigDecimal;
import java.util.Set;

public class TestUtil {
    public static CreateBookRequestDto createBookRequestDto() {
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setTitle("Title");
        requestDto.setAuthor("Author");
        requestDto.setIsbn("Isbn");
        requestDto.setPrice(new BigDecimal("10.00"));
        requestDto.setDescription("Description");
        requestDto.setCoverImage("Cover Image");
        requestDto.setCategories(Set.of(1L, 2L));

        return requestDto;
    }

    public static CreateBookRequestDto updateBook() {
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setTitle("Updated Title");
        requestDto.setAuthor("Author");
        requestDto.setIsbn("Isbn");
        requestDto.setPrice(new BigDecimal("20.00"));
        requestDto.setDescription("Description");
        requestDto.setCoverImage("Cover Image");
        requestDto.setCategories(Set.of(1L, 2L));

        return requestDto;
    }

    public static BookDto bookDto() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Title");
        bookDto.setAuthor("Author");
        bookDto.setIsbn("Isbn");
        bookDto.setPrice(new BigDecimal("10.00"));
        bookDto.setDescription("Description");
        bookDto.setCoverImage("Cover Image");
        bookDto.setCategoryIds(Set.of(1L, 2L));

        return bookDto;
    }

    public static BookDto updateBookDto() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Updated Title");
        bookDto.setAuthor("Author");
        bookDto.setIsbn("Isbn");
        bookDto.setPrice(new BigDecimal("20.00"));
        bookDto.setDescription("Description");
        bookDto.setCoverImage("Cover Image");
        bookDto.setCategoryIds(Set.of(1L, 2L));

        return bookDto;
    }

    public static CategoryDto categoryDto() {
        return new CategoryDto(2L, "Drama", "Description Drama");
    }
}
