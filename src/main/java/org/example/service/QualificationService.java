package org.example.service;

import org.example.controller.forms.QualificationSaveForm;
import org.example.controller.forms.QualificationUpdateForm;
import org.example.service.dto.QualificationDto;
import org.example.service.dto.QualificationSimpleDto;

import java.util.List;


public interface QualificationService {
    QualificationSimpleDto save(QualificationSaveForm form);

    QualificationDto findById(Long id);

    List<QualificationSimpleDto> findAll();

    void update(QualificationUpdateForm form);

    void delete(Long id);
}
