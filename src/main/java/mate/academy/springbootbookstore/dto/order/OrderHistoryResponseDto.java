package mate.academy.springbootbookstore.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import mate.academy.springbootbookstore.dto.orderItem.OrderItemDto;

public record OrderHistoryResponseDto(
        Long id,
        @NotNull
        @Positive
        Long userId,
        List<OrderItemDto> orderItems,
        LocalDateTime orderDate,
        @Positive
        BigDecimal total,
        @NotBlank
        String status
) {
}
