package mate.academy.springbootbookstore.repository.orderItem;

import mate.academy.springbootbookstore.model.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByIdAndOrderIdAndOrderUserId(Long id, Long orderId, Long userId);

    Page<OrderItem> findAllByOrderIdAndOrderUserId(Long orderId, Long id, Pageable pageable);
}
