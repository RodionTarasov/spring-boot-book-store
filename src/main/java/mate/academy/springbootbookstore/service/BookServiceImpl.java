package mate.academy.springbootbookstore.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.dto.BookDto;
import mate.academy.springbootbookstore.dto.CreateBookRequestDto;
import mate.academy.springbootbookstore.exception.EntityNotFoundException;
import mate.academy.springbootbookstore.mapper.BookMapper;
import mate.academy.springbootbookstore.model.Book;
import mate.academy.springbootbookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
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
}
