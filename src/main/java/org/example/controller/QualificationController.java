package org.example.controller;

import jakarta.validation.Valid;
import org.example.controller.forms.QualificationSaveForm;
import org.example.controller.forms.QualificationUpdateForm;
import org.example.service.QualificationService;
import org.example.service.dto.QualificationDto;
import org.example.service.dto.QualificationSimpleDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/qualifications")
public class QualificationController {
    private final QualificationService service;

    public QualificationController(QualificationService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public QualificationDto findById(@PathVariable("id")
                                     Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<QualificationSimpleDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public QualificationSimpleDto save(@Valid
                                       @RequestBody
                                       QualificationSaveForm form) {
        return service.save(form);
    }

    @PutMapping
    public void update(@Valid
                       @RequestBody
                       QualificationUpdateForm form) {
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")
                       Long id) {
        service.delete(id);
    }
}