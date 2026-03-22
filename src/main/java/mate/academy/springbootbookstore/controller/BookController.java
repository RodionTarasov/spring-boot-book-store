package mate.academy.springbootbookstore.controller;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.dto.BookDto;
import mate.academy.springbootbookstore.dto.BookSearchParameters;
import mate.academy.springbootbookstore.dto.CreateBookRequestDto;
import mate.academy.springbootbookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/search")
    public List<BookDto> search(BookSearchParameters searchParameters) {
        return bookService.search(searchParameters);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PutMapping("/{id}")
    public BookDto updateById(
            @PathVariable Long id,
            @Valid @RequestBody CreateBookRequestDto requestDto
    ) {
        return bookService.update(id, requestDto);
    }
}


