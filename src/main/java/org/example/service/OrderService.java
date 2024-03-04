package org.example.service;

import org.example.controller.forms.OrderSaveForm;
import org.example.controller.forms.OrderUpdateForm;
import org.example.service.dto.OrderDto;
import org.example.service.dto.OrderSimpleDto;

import java.util.List;

public interface OrderService {
    OrderDto save(OrderSaveForm form);

    OrderDto findById(Long id);

    List<OrderSimpleDto> findAll();

    void update(OrderUpdateForm form);

    void delete(Long id);
}
