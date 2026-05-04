package mate.academy.springbootbookstore.service.shoppingCart;

import mate.academy.springbootbookstore.dto.cartItem.CreateCartItemRequestDto;
import mate.academy.springbootbookstore.dto.cartItem.UpdateCartItemRequestDto;
import mate.academy.springbootbookstore.dto.shoppingCart.ShoppingCartDto;
import mate.academy.springbootbookstore.model.ShoppingCart;
import mate.academy.springbootbookstore.model.User;

public interface ShoppingCartService {

    ShoppingCart createCart(User user);

    ShoppingCartDto getShoppingCart();

    ShoppingCartDto addCartItem(CreateCartItemRequestDto requestDto);

    ShoppingCartDto updateCartItem(Long cartItemId, UpdateCartItemRequestDto requestDto);

    void deleteCartItem(Long cartItemId);
}
