package org.example.service;

import org.example.controller.forms.FreelancerSaveForm;
import org.example.controller.forms.FreelancerUpdateForm;
import org.example.service.dto.FreelancerDto;
import org.example.service.dto.FreelancerSimpleDto;

import java.util.List;

public interface FreelancerService {
    FreelancerDto save(FreelancerSaveForm form);

    FreelancerDto findById(Long id);

    List<FreelancerSimpleDto> findAll();

    void update(FreelancerUpdateForm form);

    void delete(Long id);
}
