package mate.academy.springbootbookstore.dto.cartItem;

public record CartItemDto(
        Long id,
        Long bookId,
        String bookTitle,
        int quantity
) {
}
