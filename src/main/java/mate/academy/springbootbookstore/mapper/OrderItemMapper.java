package mate.academy.springbootbookstore.mapper;

import mate.academy.springbootbookstore.config.MapperConfig;
import mate.academy.springbootbookstore.dto.orderItem.OrderItemDto;
import mate.academy.springbootbookstore.model.CartItem;
import mate.academy.springbootbookstore.model.Order;
import mate.academy.springbootbookstore.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {

    @Mapping(source = "book.id", target = "bookId")
    OrderItemDto toDto(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "cartItem.book", target = "book")
    @Mapping(source = "cartItem.quantity", target = "quantity")
    @Mapping(source = "cartItem.book.price", target = "price")
    @Mapping(source = "order", target = "order")
    OrderItem toModel(CartItem cartItem, Order order);

    List<OrderItemDto> toOrderItemDtoList(Collection<OrderItem> orderItems);
}
