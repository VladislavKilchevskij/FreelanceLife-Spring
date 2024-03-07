package org.example.service.impl;

import org.example.controller.forms.OrderSaveForm;
import org.example.controller.forms.OrderUpdateForm;
import org.example.exception.NoSuchDataException;
import org.example.model.OrderEntity;
import org.example.repository.OrderRepository;
import org.example.service.dto.OrderDto;
import org.example.service.dto.OrderSimpleDto;
import org.example.service.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    private OrderRepository repository;

    @Mock
    private OrderMapper mapper;

    @InjectMocks
    private OrderServiceImpl service;

    private static final String NO_DATA_MESSAGE = "No data in BD.";

    @Test
    void saveWhenOrderAbsentInDBThenReturnDto() {
        var form = new OrderSaveForm();

        var entityMock = mock(OrderEntity.class);
        var dtoMock = mock(OrderDto.class);

        when(mapper.toEntity(any(OrderSaveForm.class))).thenReturn(entityMock);
        when(repository.save(any(OrderEntity.class))).thenReturn(entityMock);
        when(mapper.toDto(any(OrderEntity.class))).thenReturn(dtoMock);

        service.save(form);

        verify(mapper, times(1)).toEntity(any(OrderSaveForm.class));
        verify(repository, times(1)).save(any(OrderEntity.class));
        verify(mapper, times(1)).toDto(any(OrderEntity.class));
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void findByIdWhenOrderExistsInBDThenReturnDto() {
        Long id = 1L;
        var entity = new OrderEntity();
        entity.setId(id);
        var optional = Optional.of(entity);

        when(repository.findById(id)).thenReturn(optional);

        service.findById(id);

        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).toDto(any(OrderEntity.class));
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void findByIdWhenOrderAbsentInBDThenThrowsNoSuchDataException() {
        Long id = 1L;
        Optional<OrderEntity> optional = Optional.empty();

        when(repository.findById(id)).thenReturn(optional);

        NoSuchDataException thrown = assertThrows(NoSuchDataException.class, () -> service.findById(id));
        assertEquals(NO_DATA_MESSAGE, thrown.getMessage());

        verify(repository, times(1)).findById(id);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void findAllWhenOrdersExistsThenReturnList() {
        var entity1 = spy(OrderEntity.class);
        var entity2 = spy(OrderEntity.class);
        var dto1 = spy(OrderSimpleDto.class);
        var dto2 = spy(OrderSimpleDto.class);
        List<OrderEntity> entities = List.of(entity1, entity2);

        when(repository.findAll()).thenReturn(entities);
        when(mapper.toSimpleDto(entity1)).thenReturn(dto1);
        when(mapper.toSimpleDto(entity2)).thenReturn(dto2);

        List<OrderSimpleDto> all = service.findAll();

        verify(repository, times(1)).findAll();
        verify(mapper, times(2)).toSimpleDto(any(OrderEntity.class));
        verifyNoMoreInteractions(repository, mapper);

        assertEquals(2, all.size());
    }

    @Test
    void findAllWhenOrdersAbsentThenThrowsNoSuchDataException() {
        List<OrderEntity> entities = Collections.emptyList();

        when(repository.findAll()).thenReturn(entities);

        NoSuchDataException thrown = assertThrows(NoSuchDataException.class, () -> service.findAll());
        assertEquals(NO_DATA_MESSAGE, thrown.getMessage());

        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository, mapper);

    }

    @Test
    void updateWhenFormValidThenMerge() {
        var form = new OrderUpdateForm();
        form.setId(1L);
        var entity = new OrderEntity();
        entity.setId(1L);

        when(mapper.toEntity(any(OrderUpdateForm.class))).thenReturn(entity);
        when(repository.save(any(OrderEntity.class))).thenReturn(null);

        service.update(form);

        verify(mapper, times(1)).toEntity(form);
        verify(repository, times(1)).save(any(OrderEntity.class));
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void deleteWhenExistAndIdNullThenFalse() {
        Long id = 1L;
        doNothing().when(repository).deleteById(id);

        service.delete(id);

        verify(repository, times(1)).deleteById(id);
        verifyNoMoreInteractions(repository);
    }
}