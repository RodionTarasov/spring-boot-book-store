package mate.academy.springbootbookstore.service.cartItem;

import mate.academy.springbootbookstore.dto.cartItem.CartItemDto;
import mate.academy.springbootbookstore.dto.cartItem.CreateCartItemRequestDto;

public interface CartItemService {

    CartItemDto addBook(CreateCartItemRequestDto requestDto);
}
