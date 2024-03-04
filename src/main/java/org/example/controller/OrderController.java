package org.example.controller;

import jakarta.validation.Valid;
import org.example.controller.forms.OrderSaveForm;
import org.example.controller.forms.OrderUpdateForm;
import org.example.service.OrderService;
import org.example.service.dto.OrderDto;
import org.example.service.dto.OrderSimpleDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public OrderDto findById(@PathVariable("id")
                                  Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<OrderSimpleDto> findAll() {
        return service.findAll();
    }


    @PostMapping
    public OrderDto save(@Valid
                     @RequestBody
                     OrderSaveForm form) {
        return service.save(form);
    }

    @PutMapping
    public void update(@Valid
                       @RequestBody
                       OrderUpdateForm form) {
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")
                       Long id) {
        service.delete(id);
    }
}