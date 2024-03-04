package org.example.service.mapper;

import org.example.controller.forms.OrderSaveForm;
import org.example.controller.forms.OrderUpdateForm;
import org.example.model.OrderEntity;
import org.example.service.dto.OrderDto;
import org.example.service.dto.OrderSimpleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    OrderEntity toEntity(OrderSaveForm form);
    OrderEntity toEntity(OrderUpdateForm form);

    OrderSimpleDto toSimpleDto(OrderEntity entity);

    OrderDto toDto(OrderEntity entity);
}
