package mate.academy.springbootbookstore.service;

import mate.academy.springbootbookstore.dto.book.BookDto;
import mate.academy.springbootbookstore.dto.book.CreateBookRequestDto;
import mate.academy.springbootbookstore.exception.EntityNotFoundException;
import mate.academy.springbootbookstore.mapper.BookMapper;
import mate.academy.springbootbookstore.model.Book;
import mate.academy.springbootbookstore.repository.book.BookRepository;
import mate.academy.springbootbookstore.service.book.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;

    @Test
    public void save_ValidCreateBookRequestDto_ReturnBookDto() {
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setTitle("A Game of Thrones");
        requestDto.setAuthor("George Martin");
        requestDto.setIsbn("231-456-789");
        requestDto.setPrice(BigDecimal.valueOf(21.99));
        requestDto.setDescription("Description");
        requestDto.setCoverImage("Cover Image");
        requestDto.setCategories(Collections.emptySet());

        Book book = new Book();
        book.setId(1L);
        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        book.setIsbn(requestDto.getIsbn());
        book.setPrice(requestDto.getPrice());
        book.setDescription(requestDto.getDescription());
        book.setCoverImage(requestDto.getCoverImage());
        book.setCategories(new HashSet<>());

        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setPrice(book.getPrice());
        bookDto.setDescription(book.getDescription());
        bookDto.setCoverImage(book.getCoverImage());

        when(bookMapper.toModel(requestDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        BookDto bookDtoSaved = bookService.save(requestDto);

        assertThat(bookDtoSaved).isEqualTo(bookDto);
        verify(bookMapper).toModel(requestDto);
        verify(bookRepository).save(book);
        verify(bookMapper).toDto(book);
    }

    @Test
    public void findById_ExistingId_ReturnsBookDto() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("A Game of Thrones");
        book.setAuthor("George Martin");
        book.setIsbn("231-456-789");
        book.setPrice(BigDecimal.valueOf(21.99));
        book.setDescription("Description");
        book.setCoverImage("Cover Image");
        book.setCategories(Collections.emptySet());

        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setPrice(book.getPrice());
        bookDto.setDescription(book.getDescription());
        bookDto.setCoverImage(book.getCoverImage());
        bookDto.setCategoryIds(Collections.emptySet());

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        BookDto actual = bookService.findById(1L);

        assertThat(actual).isEqualTo(bookDto);

        verify(bookRepository).findById(1L);
        verify(bookMapper).toDto(book);
    }

    @Test
    void findById_NonExistingId_ThrowsException() {
        when(bookRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(
                EntityNotFoundException.class,
                () -> bookService.findById(1L)
        );
        verify(bookRepository).findById(1L);
        verifyNoInteractions(bookMapper);
    }

    @Test
    void deleteById_ValidId_CallsRepository() {
        bookService.deleteById(1L);
        verify(bookRepository).deleteById(1L);
    }

    @Test
    void findAll_ValidPageable_ReturnsPageOfBookDto() {
        Pageable pageable = PageRequest.of(0, 10);
        Book book = new Book();
        book.setId(1L);
        book.setTitle("A Game of Thrones");
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("A Game of Thrones");
        Page<Book> booksPage = new PageImpl<>(List.of(book));

        when(bookRepository.findAll(pageable)).thenReturn(booksPage);
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        Page<BookDto> actual = bookService.findAll(pageable);
        assertThat(actual.getTotalElements()).isEqualTo(1);
        assertThat(actual.getContent().get(0)).isEqualTo(bookDto);

        verify(bookRepository).findAll(pageable);
        verify(bookMapper).toDto(book);
    }

    @Test
    void update_ExistingBook_ReturnsUpdatedBookDto() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Old title");
        book.setAuthor("Old author");
        book.setIsbn("111");
        book.setPrice(BigDecimal.ONE);
        book.setDescription("Old description");
        book.setCoverImage("Old image");
        book.setCategories(new HashSet<>());

        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setTitle("New title");
        requestDto.setAuthor("New author");
        requestDto.setIsbn("222");
        requestDto.setPrice(BigDecimal.TEN);
        requestDto.setDescription("New description");
        requestDto.setCoverImage("New image");
        requestDto.setCategories(Collections.emptySet());

        BookDto expected = new BookDto();
        expected.setId(1L);
        expected.setTitle("New title");
        expected.setAuthor("New author");
        expected.setIsbn("222");
        expected.setPrice(BigDecimal.TEN);
        expected.setDescription("New description");
        expected.setCoverImage("New image");
        expected.setCategoryIds(Collections.emptySet());

        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(book));

        when(bookRepository.save(any(Book.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(bookMapper.toDto(any(Book.class)))
                .thenReturn(expected);

        BookDto actual = bookService.update(1L, requestDto);

        assertThat(actual).isEqualTo(expected);

        assertThat(book.getTitle()).isEqualTo("New title");
        assertThat(book.getAuthor()).isEqualTo("New author");
        assertThat(book.getIsbn()).isEqualTo("222");
        assertThat(book.getPrice()).isEqualTo(BigDecimal.TEN);
        assertThat(book.getDescription()).isEqualTo("New description");
        assertThat(book.getCoverImage()).isEqualTo("New image");

        verify(bookRepository).findById(1L);
        verify(bookRepository).save(book);
        verify(bookMapper).toDto(book);
    }

    @Test
    void update_NonExistingBook_ThrowsException() {
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setTitle("New title");
        requestDto.setAuthor("New author");
        requestDto.setIsbn("222");
        requestDto.setPrice(BigDecimal.TEN);
        requestDto.setCategories(Collections.emptySet());

        when(bookRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> bookService.update(1L, requestDto)
        );

        verify(bookRepository).findById(1L);
        verify(bookRepository, never()).save(any(Book.class));
        verifyNoInteractions(bookMapper);
    }
}
