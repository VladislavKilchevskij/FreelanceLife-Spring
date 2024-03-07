package org.example.service.impl;

import org.example.controller.forms.QualificationSaveForm;
import org.example.controller.forms.QualificationUpdateForm;
import org.example.exception.NoSuchDataException;
import org.example.model.QualificationEntity;
import org.example.repository.QualificationRepository;
import org.example.service.dto.QualificationSimpleDto;
import org.example.service.mapper.QualificationMapper;
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
class QualificationServiceImplTest {
    @Mock
    private QualificationRepository repository;

    @Mock
    private QualificationMapper mapper;

    @InjectMocks
    private QualificationServiceImpl service;

    private static final String NO_DATA_MESSAGE = "No data in BD.";

    @Test
    void saveWhenQualificationAbsentInDBThenReturnDto() {
        var form = new QualificationSaveForm();

        var entityMock = mock(QualificationEntity.class);
        var dtoMock = mock(QualificationSimpleDto.class);

        when(mapper.toEntity(any(QualificationSaveForm.class))).thenReturn(entityMock);
        when(repository.save(any(QualificationEntity.class))).thenReturn(entityMock);
        when(mapper.toSimpleDto(any(QualificationEntity.class))).thenReturn(dtoMock);

        service.save(form);

        verify(mapper, times(1)).toEntity(any(QualificationSaveForm.class));
        verify(repository, times(1)).save(any(QualificationEntity.class));
        verify(mapper, times(1)).toSimpleDto(any(QualificationEntity.class));
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void findByIdWhenQualificationExistsInBDThenReturnDto() {
        Long id = 1L;
        var entity = new QualificationEntity();
        entity.setId(id);
        var optional = Optional.of(entity);

        when(repository.findById(id)).thenReturn(optional);

        service.findById(id);

        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).toDto(any(QualificationEntity.class));
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void findByIdWhenQualificationAbsentInBDThenThrowsNoSuchDataException() {
        Long id = 1L;
        Optional<QualificationEntity> optional = Optional.empty();

        when(repository.findById(id)).thenReturn(optional);

        NoSuchDataException thrown = assertThrows(NoSuchDataException.class, () -> service.findById(id));
        assertEquals(NO_DATA_MESSAGE, thrown.getMessage());

        verify(repository, times(1)).findById(id);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void findAllWhenQualificationsExistsThenReturnList() {
        var entity1 = spy(QualificationEntity.class);
        var entity2 = spy(QualificationEntity.class);
        var dto1 = spy(QualificationSimpleDto.class);
        var dto2 = spy(QualificationSimpleDto.class);
        List<QualificationEntity> entities = List.of(entity1, entity2);

        when(repository.findAll()).thenReturn(entities);
        when(mapper.toSimpleDto(entity1)).thenReturn(dto1);
        when(mapper.toSimpleDto(entity2)).thenReturn(dto2);

        List<QualificationSimpleDto> all = service.findAll();

        verify(repository, times(1)).findAll();
        verify(mapper, times(2)).toSimpleDto(any(QualificationEntity.class));
        verifyNoMoreInteractions(repository, mapper);

        assertEquals(2, all.size());
    }

    @Test
    void findAllWhenQualificationsAbsentThenThrowsNoSuchDataException() {
        List<QualificationEntity> entities = Collections.emptyList();

        when(repository.findAll()).thenReturn(entities);

        NoSuchDataException thrown = assertThrows(NoSuchDataException.class, () -> service.findAll());
        assertEquals(NO_DATA_MESSAGE, thrown.getMessage());

        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository, mapper);

    }

    @Test
    void updateWhenFormValidThenMerge() {
        var form = new QualificationUpdateForm();
        form.setId(1L);
        var entity = new QualificationEntity();
        entity.setId(1L);

        when(mapper.toEntity(any(QualificationUpdateForm.class))).thenReturn(entity);
        when(repository.save(any(QualificationEntity.class))).thenReturn(null);

        service.update(form);

        verify(mapper, times(1)).toEntity(form);
        verify(repository, times(1)).save(any(QualificationEntity.class));
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