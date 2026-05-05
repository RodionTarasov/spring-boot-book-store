package mate.academy.springbootbookstore.mapper;

import mate.academy.springbootbookstore.config.MapperConfig;
import mate.academy.springbootbookstore.dto.order.OrderDto;
import mate.academy.springbootbookstore.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orderItems", target = "orderItems")
    OrderDto toDto(Order order);
}
