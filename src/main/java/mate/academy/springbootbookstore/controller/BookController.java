package mate.academy.springbootbookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.dto.book.BookDto;
import mate.academy.springbootbookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.springbootbookstore.dto.book.BookSearchParameters;
import mate.academy.springbootbookstore.dto.book.CreateBookRequestDto;
import mate.academy.springbootbookstore.service.book.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Book management", description = "Endpoint for managing book")
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books", description = "Get a list of all books")
    public Page<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get one book", description = "Finds a book by id")
    public BookDto getById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/search")
    public Page<BookDto> search(BookSearchParameters searchParameters, Pageable pageable) {
        return bookService.search(searchParameters, pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new book", description = "Create a new book")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Delete a book")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BookDto updateById(
            @PathVariable Long id,
            @Valid @RequestBody CreateBookRequestDto requestDto
    ) {
        return bookService.update(id, requestDto);
    }

    @GetMapping("/{id}/books")
    @Operation(
            summary = "Get books by category",
            description = "Returns paginated book belonging to the category"
    )
    public Page<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id, Pageable pageable) {
        return bookService.getBooksWithoutCategoryId(id, pageable);
    }
}


