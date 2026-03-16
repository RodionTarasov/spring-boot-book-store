package mate.academy.springbootbookstore.service;

import java.util.List;
import mate.academy.springbootbookstore.dto.BookDto;
import mate.academy.springbootbookstore.dto.BookSearchParameters;
import mate.academy.springbootbookstore.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findById(long id);

    void deleteById(long id);

    List<BookDto> search(BookSearchParameters searchParameters);
}
