package mate.academy.springbootbookstore.repository.cartItem;

import mate.academy.springbootbookstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
