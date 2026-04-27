package mate.academy.springbootbookstore.service.shoppingCart;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.dto.cartItem.CreateCartItemRequestDto;
import mate.academy.springbootbookstore.dto.cartItem.UpdateCartItemRequestDto;
import mate.academy.springbootbookstore.dto.shoppingCart.ShoppingCartDto;
import mate.academy.springbootbookstore.exception.EntityNotFoundException;
import mate.academy.springbootbookstore.mapper.ShoppingCartMapper;
import mate.academy.springbootbookstore.model.Book;
import mate.academy.springbootbookstore.model.CartItem;
import mate.academy.springbootbookstore.model.ShoppingCart;
import mate.academy.springbootbookstore.model.User;
import mate.academy.springbootbookstore.repository.book.BookRepository;
import mate.academy.springbootbookstore.repository.cartItem.CartItemRepository;
import mate.academy.springbootbookstore.repository.shoppingCart.ShoppingCartRepository;
import mate.academy.springbootbookstore.service.user.CurrentUserService;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CurrentUserService currentUserService;
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public ShoppingCart createCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setCartItems(new HashSet<>());

        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartDto showShoppingCart() {
        ShoppingCart shoppingCart = findShoppingCart();
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto addCartItem(CreateCartItemRequestDto requestDto) {
        ShoppingCart shoppingCart = findShoppingCart();
        Book book = bookRepository.findById(requestDto.bookId()).orElseThrow(
                () -> new EntityNotFoundException("Book not found by id" + requestDto.bookId())
        );

        Optional<CartItem> existingItem = shoppingCart.getCartItems()
                .stream()
                .filter(i -> i.getBook().getId().equals(requestDto.bookId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + requestDto.quantity());
        } else {
            CartItem item = new CartItem();
            item.setShoppingCart(shoppingCart);
            item.setQuantity(requestDto.quantity());
            item.setBook(book);
            shoppingCart.getCartItems().add(item);
        }

        return shoppingCartMapper.toDto(shoppingCartRepository.save(shoppingCart));
    }

    @Override
    public ShoppingCartDto updateCartItem(Long cartItemId, UpdateCartItemRequestDto requestDto) {
        ShoppingCart shoppingCart = findShoppingCart();
        CartItem cartItem = shoppingCart.getCartItems().stream()
                .filter(s -> s.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found by id" + cartItemId));

        cartItem.setQuantity(requestDto.quantity());

        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    private ShoppingCart findShoppingCart() {
        User user = currentUserService.getCurrentUser();
        return shoppingCartRepository.findByUserId(user.getId()).orElseThrow(
                () -> new EntityNotFoundException("Shopping cart not found")
        );
    }
}
