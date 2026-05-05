package mate.academy.springbootbookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.dto.order.CreateOrderRequestDto;
import mate.academy.springbootbookstore.dto.order.OrderDto;
import mate.academy.springbootbookstore.dto.order.UpdateOrderStatusDto;
import mate.academy.springbootbookstore.dto.orderItem.OrderItemDto;
import mate.academy.springbootbookstore.service.order.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(
        name = "Order Controller",
        description = "Operations related to orders: create, view, update status, " +
                "and manage order items"
)
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create order",
            description = "Creates a new order from the user's shopping cart"
    )
    public OrderDto createOrder(@RequestBody @Valid CreateOrderRequestDto requestDto) {
        return orderService.createOrder(requestDto);
    }

    @GetMapping
    @Operation(
            summary = "Get order history",
            description = "Returns the list of user's orders"
    )
    public Page<OrderDto> getAll(Pageable pageable) {
        return orderService.findAll(pageable);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Update order status",
            description = "Allows admin to update order status"
    )
    public OrderDto updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateOrderStatusDto requestDto
    ) {
        return orderService.updateStatus(id, requestDto);
    }

    @GetMapping("/{orderId}/items")
    @Operation(
            summary = "Get order items",
            description = "Returns all items for a specific order"
    )
    public Page<OrderItemDto> getAllOrderItems(@PathVariable Long orderId, Pageable pageable) {
        return orderService.findAllOrderItems(orderId, pageable);
    }

    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(
            summary = "Get order item",
            description = "Returns a specific item inside an order"
    )
    public OrderItemDto getOrderItem(
            @PathVariable Long orderId,
            @PathVariable Long itemId
    ) {
        return orderService.findOrderItem(orderId, itemId);
    }
}
