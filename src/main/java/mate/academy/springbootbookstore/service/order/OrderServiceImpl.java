package mate.academy.springbootbookstore.service.order;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.dto.order.CreateOrderRequestDto;
import mate.academy.springbootbookstore.dto.order.OrderDto;
import mate.academy.springbootbookstore.dto.order.UpdateOrderStatusDto;
import mate.academy.springbootbookstore.dto.orderItem.OrderItemDto;
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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
            throw new RuntimeException("Shopping cart is empty");
        }
        Order order = new Order();
        Set<OrderItem> orderItems = toOrderItems(cartItems, order);

        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderItems(orderItems);
        order.setStatus(Order.Status.NEW);
        order.setTotal(totalAmount(orderItems));
        order.setShippingAddress(requestDto.shippingAddress());

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
                () -> new RuntimeException("Order not found: " + orderId)
        );
        order.setStatus(requestDto.status());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public Page<OrderItemDto> findAllOrderItems(Long orderId, Pageable pageable) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Order not found: " + orderId)
        );
        verification(order);

        return orderItemRepository.findAllByOrderId(order.getId(), pageable)
                .map(orderItemMapper::toDto);
    }

    @Override
    public OrderItemDto findOrderItem(Long orderId, Long orderItemId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Order not found: " + orderId)
        );
        verification(order);

        OrderItem item = order.getOrderItems().stream()
                .filter(orderItem -> orderItem.getId().equals(orderItemId))
                .findFirst()
                .orElseThrow(() -> new AccessDeniedException("Order item not found: " + orderItemId));
        return orderItemMapper.toDto(item);
    }

    private void verification(Order order) {
        User user = currentUserService.getCurrentUser();
        if (!order.getUser().getId().equals(user.getId())) {
            throw new EntityNotFoundException("User not authorized to order!");
        }
    }

    private Set<OrderItem> toOrderItems(Set<CartItem> cartItems, Order order) {
        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getBook().getPrice());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        return orderItems;
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
