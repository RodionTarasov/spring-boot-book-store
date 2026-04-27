package mate.academy.springbootbookstore.service.cartItem;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.dto.cartItem.CartItemDto;
import mate.academy.springbootbookstore.dto.cartItem.CreateCartItemRequestDto;
import mate.academy.springbootbookstore.exception.EntityNotFoundException;
import mate.academy.springbootbookstore.model.Book;
import mate.academy.springbootbookstore.repository.book.BookRepository;
import mate.academy.springbootbookstore.repository.cartItem.CartItemRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;

    @Override
    public CartItemDto addBook(CreateCartItemRequestDto requestDto) {
        Book book = bookRepository.findById(requestDto.bookId()).orElseThrow(
                () -> new EntityNotFoundException("Book not found" + requestDto.bookId())
        );

        return null;
    }
}
