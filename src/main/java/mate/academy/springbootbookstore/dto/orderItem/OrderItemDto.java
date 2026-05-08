package mate.academy.springbootbookstore.dto.orderItem;

public record OrderItemDto(
        Long id,
        Long bookId,
        int quantity
) {
}
