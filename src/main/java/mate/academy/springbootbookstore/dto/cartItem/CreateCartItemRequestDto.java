package mate.academy.springbootbookstore.dto.cartItem;

public record CreateCartItemRequestDto(Long bookId, int quantity) {
}
