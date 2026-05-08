package mate.academy.springbootbookstore.service.order;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.dto.order.CreateOrderRequestDto;
import mate.academy.springbootbookstore.dto.order.OrderDto;
import mate.academy.springbootbookstore.dto.order.UpdateOrderStatusDto;
import mate.academy.springbootbookstore.dto.orderItem.OrderItemDto;
import mate.academy.springbootbookstore.exception.OrderProcessingException;
import mate.academy.springbootbookstore.exception.EntityNotFoundException;
import mate.academy.springbootbookstore.mapper.OrderItemMapper;
import mate.academy.springbootbookstore.mapper.OrderMapper;
import mate.academy.springbootbookstore.model.CartItem;
import mate.academy.springbootbookstore.model.Order;
import mate.academy.springbootbookstore.model.OrderItem;
import mate.academy.springbootbookstore.model.ShoppingCart;
import mate.academy.springbootbookstore.model.User;
import mate.academy.springbootbookstore.repository.order.OrderRepository;
import mate.academy.springbootbookstore.repository.orderItem.OrderItemRepository;
import mate.academy.springbootbookstore.repository.shoppingCart.ShoppingCartRepository;
import mate.academy.springbootbookstore.service.user.CurrentUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CurrentUserService currentUserService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderDto createOrder(CreateOrderRequestDto requestDto) {
        User user = currentUserService.getCurrentUser();
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId()).orElseThrow(
                () -> new RuntimeException("Shopping cart not found: " + user.getId())
        );
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        if (cartItems.isEmpty()) {
            throw new OrderProcessingException("Shopping cart with id: " +
                    shoppingCart.getId() + " is empty");
        }
        Order order = orderMapper.toModel(requestDto, user);
        Set<OrderItem> orderItems = toOrderItems(cartItems, order);

        order.setOrderItems(orderItems);
        order.setTotal(totalAmount(orderItems));

        Order saveOrder = orderRepository.save(order);
        shoppingCart.getCartItems().clear();
        shoppingCartRepository.save(shoppingCart);

        return orderMapper.toDto(saveOrder);
    }

    @Override
    public Page<OrderDto> findAll(Pageable pageable) {
        User user = currentUserService.getCurrentUser();
        return orderRepository.findAllByUserId(user.getId(), pageable).map(orderMapper::toDto);
    }

    @Override
    public OrderDto updateStatus(Long orderId, UpdateOrderStatusDto requestDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Order not found: " + orderId)
        );
        order.setStatus(requestDto.status());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public Page<OrderItemDto> findAllOrderItems(Long orderId, Pageable pageable) {
        User user = currentUserService.getCurrentUser();
        return orderItemRepository
                .findAllByOrderIdAndOrderUserId(
                        orderId,
                        user.getId(),
                        pageable
                )
                .map(orderItemMapper::toDto);
    }

    @Override
    public OrderItemDto findOrderItem(Long orderId, Long orderItemId) {
        User user = currentUserService.getCurrentUser();
        OrderItem item = orderItemRepository
                .findByIdAndOrderIdAndOrderUserId(orderItemId, orderId, user.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Order item not found: " + orderItemId)
                );
        return orderItemMapper.toDto(item);
    }

    private Set<OrderItem> toOrderItems(Set<CartItem> cartItems, Order order) {
        return cartItems.stream()
                .map(cartItem -> orderItemMapper.toModel(cartItem, order))
                .collect(Collectors.toSet());
    }

    private BigDecimal totalAmount(Set<OrderItem> orderItems) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            BigDecimal itemTotal = BigDecimal.valueOf(orderItem.getQuantity())
                    .multiply(orderItem.getPrice());
            total = total.add(itemTotal);
        }
        return total;
    }
}
