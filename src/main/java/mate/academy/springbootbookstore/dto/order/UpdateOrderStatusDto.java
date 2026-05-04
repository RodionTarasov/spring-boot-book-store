package mate.academy.springbootbookstore.dto.order;

import jakarta.validation.constraints.NotBlank;
import mate.academy.springbootbookstore.model.Order;

public record UpdateOrderStatusDto (@NotBlank Order.Status status) {
}
