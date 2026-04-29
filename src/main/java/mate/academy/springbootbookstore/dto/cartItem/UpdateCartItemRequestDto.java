package mate.academy.springbootbookstore.dto.cartItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateCartItemRequestDto(
        @NotNull
        @Positive
        int quantity
) {
}
