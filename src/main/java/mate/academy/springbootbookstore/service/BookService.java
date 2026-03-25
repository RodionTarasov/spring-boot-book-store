package mate.academy.springbootbookstore.service;

import mate.academy.springbootbookstore.dto.BookDto;
import mate.academy.springbootbookstore.dto.BookSearchParameters;
import mate.academy.springbootbookstore.dto.CreateBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    Page<BookDto> findAll(Pageable pageable);

    BookDto findById(long id);

    void deleteById(long id);

    Page<BookDto> search(BookSearchParameters searchParameters, Pageable pageable);

    BookDto update(Long id, CreateBookRequestDto requestDto);
}
