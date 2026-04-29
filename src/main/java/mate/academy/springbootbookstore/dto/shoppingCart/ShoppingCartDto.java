package mate.academy.springbootbookstore.dto.shoppingCart;

import mate.academy.springbootbookstore.dto.cartItem.CartItemDto;
import java.util.List;

public record ShoppingCartDto(
        Long id,
        Long userId,
        List<CartItemDto> cartItems
) {
}
