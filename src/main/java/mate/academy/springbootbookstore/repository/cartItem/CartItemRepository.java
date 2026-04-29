package mate.academy.springbootbookstore.repository.cartItem;

import mate.academy.springbootbookstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
                                                                                                                                    
    Optional<CartItem> findByIdAndShoppingCartId(Long id, Long shoppingCartId);
}
