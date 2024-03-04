package org.example.service.impl;

import org.example.controller.forms.FreelancerSaveForm;
import org.example.controller.forms.FreelancerUpdateForm;
import org.example.exception.AlreadyExistsException;
import org.example.exception.NoSuchDataException;
import org.example.model.FreelancerEntity;
import org.example.repository.FreelancerRepository;
import org.example.service.FreelancerService;
import org.example.service.dto.FreelancerDto;
import org.example.service.dto.FreelancerSimpleDto;
import org.example.service.mapper.FreelancerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreelancerServiceImpl implements FreelancerService {
    private final FreelancerRepository repository;
    private final FreelancerMapper mapper;

    public FreelancerServiceImpl(FreelancerRepository repository, FreelancerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public FreelancerDto save(FreelancerSaveForm form) {
        if (repository.existsByFreelancerEmail(form.getFreelancerEmail()))
            throw new AlreadyExistsException("Such freelancer already exists!");
        return mapper.toDto(repository.save(mapper.toEntity(form)));
    }

    @Override
    public FreelancerDto findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new NoSuchDataException("No data in BD.")));
    }

    @Override
    public List<FreelancerSimpleDto> findAll() {
        List<FreelancerEntity> entities = repository.findAll();
        if (entities.isEmpty()) throw new NoSuchDataException("No data in BD.");
        return entities.stream().map(mapper::toSimpleDto).toList();
    }

    @Override
    public void update(FreelancerUpdateForm form) {
        repository.save(mapper.toEntity(form));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}