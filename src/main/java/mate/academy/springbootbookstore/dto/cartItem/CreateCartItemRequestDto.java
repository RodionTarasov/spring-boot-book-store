package mate.academy.springbootbookstore.dto.cartItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateCartItemRequestDto(
        Long bookId,
        @NotNull
        @Positive
        int quantity) {
}
