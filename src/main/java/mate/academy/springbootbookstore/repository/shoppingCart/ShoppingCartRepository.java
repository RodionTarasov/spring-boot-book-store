package mate.academy.springbootbookstore.repository.shoppingCart;

import mate.academy.springbootbookstore.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserId(Long userId);
}
