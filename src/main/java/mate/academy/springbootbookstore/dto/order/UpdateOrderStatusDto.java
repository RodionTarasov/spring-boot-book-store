package mate.academy.springbootbookstore.dto.order;

import jakarta.validation.constraints.NotNull;
import mate.academy.springbootbookstore.model.Order;

public record UpdateOrderStatusDto (
        @NotNull Order.Status status
) {
}
