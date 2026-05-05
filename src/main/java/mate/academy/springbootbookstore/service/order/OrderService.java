package mate.academy.springbootbookstore.service.order;

import mate.academy.springbootbookstore.dto.order.CreateOrderRequestDto;
import mate.academy.springbootbookstore.dto.order.OrderDto;
import mate.academy.springbootbookstore.dto.order.UpdateOrderStatusDto;
import mate.academy.springbootbookstore.dto.orderItem.OrderItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderDto createOrder(CreateOrderRequestDto requestDto);

    Page<OrderDto> findAll(Pageable pageable);

    OrderDto updateStatus(Long id, UpdateOrderStatusDto requestDto);

    Page<OrderItemDto> findAllOrderItems(Long orderId, Pageable pageable);

    OrderItemDto findOrderItem(Long orderId, Long orderItemId);
}
