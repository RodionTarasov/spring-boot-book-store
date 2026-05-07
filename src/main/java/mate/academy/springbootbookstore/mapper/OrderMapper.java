package mate.academy.springbootbookstore.mapper;

import mate.academy.springbootbookstore.config.MapperConfig;
import mate.academy.springbootbookstore.dto.order.CreateOrderRequestDto;
import mate.academy.springbootbookstore.dto.order.OrderDto;
import mate.academy.springbootbookstore.model.Order;
import mate.academy.springbootbookstore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    OrderDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "user", target = "user")
    @Mapping(source = "requestDto.shippingAddress", target = "shippingAddress")
    @Mapping(target = "orderDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "status", expression = "java(Order.Status.NEW)")
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "total", ignore = true)
    Order toModel(CreateOrderRequestDto requestDto, User user);
}
