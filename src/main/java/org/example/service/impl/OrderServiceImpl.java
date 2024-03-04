package org.example.service.impl;

import org.example.controller.forms.OrderSaveForm;
import org.example.controller.forms.OrderUpdateForm;
import org.example.exception.NoSuchDataException;
import org.example.model.OrderEntity;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
import org.example.service.dto.OrderDto;
import org.example.service.dto.OrderSimpleDto;
import org.example.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;

    public OrderServiceImpl(OrderRepository repository, OrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public OrderDto save(OrderSaveForm form) {
        return mapper.toDto(repository.save(mapper.toEntity(form)));
    }

    @Override
    public OrderDto findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new NoSuchDataException("No data in BD.")));
    }

    @Override
    public List<OrderSimpleDto> findAll() {
        List<OrderEntity> entities = repository.findAll();
        if (entities.isEmpty()) throw new NoSuchDataException("No data in BD.");
        return entities.stream().map(mapper::toSimpleDto).toList();
    }

    @Override
    public void update(OrderUpdateForm form) {
        repository.save(mapper.toEntity(form));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}