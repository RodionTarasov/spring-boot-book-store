package mate.academy.springbootbookstore.service.book;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.dto.book.BookDto;
import mate.academy.springbootbookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.springbootbookstore.dto.book.BookSearchParameters;
import mate.academy.springbootbookstore.dto.book.CreateBookRequestDto;
import mate.academy.springbootbookstore.exception.EntityNotFoundException;
import mate.academy.springbootbookstore.mapper.BookMapper;
import mate.academy.springbootbookstore.model.Book;
import mate.academy.springbootbookstore.model.Category;
import mate.academy.springbootbookstore.repository.book.BookRepository;
import mate.academy.springbootbookstore.repository.book.BookSpecificationBuilder;
import mate.academy.springbootbookstore.repository.category.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;
    private final CategoryRepository categoryRepository;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        if (requestDto.getCategories() != null && !requestDto.getCategories().isEmpty()) {
            mapCategories(requestDto, book);
        }
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookDto findById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book not found" + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<BookDto> search(BookSearchParameters searchParameters, Pageable pageable) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(searchParameters);
        return bookRepository.findAll(bookSpecification, pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto requestDto) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book not found" + id)
        );
        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        book.setIsbn(requestDto.getIsbn());
        book.setPrice(requestDto.getPrice());
        book.setDescription(requestDto.getDescription());
        book.setCoverImage(requestDto.getCoverImage());

        if (requestDto.getCategories() != null) {
            if (requestDto.getCategories().isEmpty()) {
                book.setCategories(new HashSet<>());
            } else {
                mapCategories(requestDto, book);
            }
        }

        return bookMapper.toDto(bookRepository.save(book));

    }

    @Override
    public Page<BookDtoWithoutCategoryIds> getBooksWithoutCategoryId(Long id, Pageable pageable) {
        return bookRepository.findAllByCategories_Id(id, pageable);
    }

    private void mapCategories(CreateBookRequestDto requestDto, Book book) {
        Set<Long> requestedIds = new HashSet<>(requestDto.getCategories());
        List<Category> categories = categoryRepository.findAllById(requestedIds);
        if (categories.size() != requestedIds.size()) {
            throw new EntityNotFoundException("One or more categories not found");
        }
        book.setCategories(new HashSet<>(categories));
    }
}
