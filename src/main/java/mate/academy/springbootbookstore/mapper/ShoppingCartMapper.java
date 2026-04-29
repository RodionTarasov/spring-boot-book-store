package mate.academy.springbootbookstore.mapper;

import mate.academy.springbootbookstore.config.MapperConfig;
import mate.academy.springbootbookstore.dto.shoppingCart.ShoppingCartDto;
import mate.academy.springbootbookstore.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {

    @Mapping(source = "user.id", target = "userId")
    ShoppingCartDto toDto(ShoppingCart shoppingCart);
}
