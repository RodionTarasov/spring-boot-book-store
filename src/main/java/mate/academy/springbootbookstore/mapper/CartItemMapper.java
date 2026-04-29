package mate.academy.springbootbookstore.mapper;

import mate.academy.springbootbookstore.config.MapperConfig;
import mate.academy.springbootbookstore.dto.cartItem.CartItemDto;
import mate.academy.springbootbookstore.dto.cartItem.CreateCartItemRequestDto;
import mate.academy.springbootbookstore.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = BookMapper.class)
public interface CartItemMapper {

    @Mapping(target = "book", source = "bookId")
    CartItem toModel(CreateCartItemRequestDto request);

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    CartItemDto toDto(CartItem item);
}
