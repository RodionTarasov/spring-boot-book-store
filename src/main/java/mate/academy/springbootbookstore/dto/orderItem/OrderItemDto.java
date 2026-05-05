package mate.academy.springbootbookstore.dto.orderItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemDto(
        Long id,
        @NotNull
        @Positive
        Long bookId,
        @Positive
        int quantity
) {
}
