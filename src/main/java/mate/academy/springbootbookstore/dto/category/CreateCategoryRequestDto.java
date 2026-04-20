package mate.academy.springbootbookstore.dto.category;

public record CreateCategoryRequestDto(
    String name,
    String description
) {
}
