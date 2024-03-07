package org.example.service.impl;

import org.example.controller.forms.FreelancerSaveForm;
import org.example.controller.forms.FreelancerUpdateForm;
import org.example.exception.AlreadyExistsException;
import org.example.exception.NoSuchDataException;
import org.example.model.FreelancerEntity;
import org.example.repository.FreelancerRepository;
import org.example.service.dto.FreelancerDto;
import org.example.service.dto.FreelancerSimpleDto;
import org.example.service.mapper.FreelancerMapper;
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
class FreelancerServiceImplTest {
    @Mock
    private FreelancerRepository repository;

    @Mock
    private FreelancerMapper mapper;

    @InjectMocks
    private FreelancerServiceImpl service;

    private static final String NO_DATA_MESSAGE = "No data in BD.";

    @Test
    void saveWhenFreelancerAbsentInDBThenReturnDto() {
        var form = new FreelancerSaveForm();
        form.setFreelancerEmail("test@test.com");

        var entityMock = mock(FreelancerEntity.class);
        var dtoMock = mock(FreelancerDto.class);

        when(repository.existsByFreelancerEmail(form.getFreelancerEmail())).thenReturn(false);
        when(mapper.toEntity(any(FreelancerSaveForm.class))).thenReturn(entityMock);
        when(repository.save(any(FreelancerEntity.class))).thenReturn(entityMock);
        when(mapper.toDto(any(FreelancerEntity.class))).thenReturn(dtoMock);

        service.save(form);

        verify(repository, times(1)).existsByFreelancerEmail(anyString());
        verify(mapper, times(1)).toEntity(any(FreelancerSaveForm.class));
        verify(repository, times(1)).save(any(FreelancerEntity.class));
        verify(mapper, times(1)).toDto(any(FreelancerEntity.class));
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void saveWhenFreelancerExistsInDBThenThrowsAlreadyExistsException() {
        var form = new FreelancerSaveForm();
        form.setFreelancerEmail("test@test.com");

        when(repository.existsByFreelancerEmail(form.getFreelancerEmail())).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> service.save(form), "Such freelancer already exists!");

        verify(repository, times(1)).existsByFreelancerEmail(anyString());
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void findByIdWhenFreelancerExistsInBDThenReturnDto() {
        Long id = 1L;
        var entity = new FreelancerEntity();
        entity.setId(id);
        var optional = Optional.of(entity);

        when(repository.findById(id)).thenReturn(optional);

        service.findById(id);

        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).toDto(any(FreelancerEntity.class));
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void findByIdWhenFreelancerAbsentInBDThenThrowsNoSuchDataException() {
        Long id = 1L;
        Optional<FreelancerEntity> optional = Optional.empty();

        when(repository.findById(id)).thenReturn(optional);

        NoSuchDataException thrown = assertThrows(NoSuchDataException.class, () -> service.findById(id));
        assertEquals(NO_DATA_MESSAGE, thrown.getMessage());

        verify(repository, times(1)).findById(id);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void findAllWhenFreelancersExistsThenReturnList() {
        var entity1 = spy(FreelancerEntity.class);
        var entity2 = spy(FreelancerEntity.class);
        var dto1 = spy(FreelancerSimpleDto.class);
        var dto2 = spy(FreelancerSimpleDto.class);
        List<FreelancerEntity> entities = List.of(entity1, entity2);

        when(repository.findAll()).thenReturn(entities);
        when(mapper.toSimpleDto(entity1)).thenReturn(dto1);
        when(mapper.toSimpleDto(entity2)).thenReturn(dto2);

        List<FreelancerSimpleDto> all = service.findAll();

        verify(repository, times(1)).findAll();
        verify(mapper, times(2)).toSimpleDto(any(FreelancerEntity.class));
        verifyNoMoreInteractions(repository, mapper);

        assertEquals(2, all.size());
    }

    @Test
    void findAllWhenFreelancersAbsentThenThrowsNoSuchDataException() {
        List<FreelancerEntity> entities = Collections.emptyList();

        when(repository.findAll()).thenReturn(entities);

        NoSuchDataException thrown = assertThrows(NoSuchDataException.class, () -> service.findAll());
        assertEquals(NO_DATA_MESSAGE, thrown.getMessage());

        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository, mapper);

    }

    @Test
    void updateWhenFormValidThenMerge() {
        var form = new FreelancerUpdateForm();
        form.setId(1L);
        var entity = new FreelancerEntity();
        entity.setId(1L);

        when(mapper.toEntity(any(FreelancerUpdateForm.class))).thenReturn(entity);
        when(repository.save(any(FreelancerEntity.class))).thenReturn(null);

        service.update(form);

        verify(mapper, times(1)).toEntity(form);
        verify(repository, times(1)).save(any(FreelancerEntity.class));
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