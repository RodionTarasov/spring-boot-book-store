package mate.academy.springbootbookstore.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import mate.academy.springbootbookstore.dto.orderItem.OrderItemDto;

public record OrderHistoryResponseDto(
        Long id,
        Long userId,
        List<OrderItemDto> orderItems,
        LocalDateTime orderDate,
        BigDecimal total,
        String status
) {
}
