package mate.academy.springbootbookstore.dto.order;

import mate.academy.springbootbookstore.model.Order;

public record UpdateOrderStatusDto (Order.Status status) {
}
