package org.example.service.impl;

import org.example.controller.forms.QualificationSaveForm;
import org.example.controller.forms.QualificationUpdateForm;
import org.example.exception.NoSuchDataException;
import org.example.model.QualificationEntity;
import org.example.repository.QualificationRepository;
import org.example.service.QualificationService;
import org.example.service.dto.QualificationDto;
import org.example.service.dto.QualificationSimpleDto;
import org.example.service.mapper.QualificationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService {
    private final QualificationRepository repository;
    private final QualificationMapper mapper;

    public QualificationServiceImpl(QualificationRepository repository, QualificationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public QualificationSimpleDto save(QualificationSaveForm form) {
        return mapper.toSimpleDto(repository.save(mapper.toEntity(form)));
    }

    @Override
    public QualificationDto findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new NoSuchDataException("No data in BD.")));
    }

    @Override
    public List<QualificationSimpleDto> findAll() {
        List<QualificationEntity> entities = repository.findAll();
        if (entities.isEmpty()) throw new NoSuchDataException("No data in BD.");
        return entities.stream().map(mapper::toSimpleDto).toList();
    }

    @Override
    public void update(QualificationUpdateForm form) {
        repository.save(mapper.toEntity(form));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}