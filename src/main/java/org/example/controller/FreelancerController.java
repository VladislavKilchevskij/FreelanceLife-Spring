package org.example.controller;

import jakarta.validation.Valid;
import org.example.controller.forms.FreelancerSaveForm;
import org.example.controller.forms.FreelancerUpdateForm;
import org.example.service.FreelancerService;
import org.example.service.dto.FreelancerDto;
import org.example.service.dto.FreelancerSimpleDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/freelancers")
public class FreelancerController {
    private final FreelancerService service;

    public FreelancerController(FreelancerService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public FreelancerDto findById(@PathVariable("id")
                                  Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<FreelancerSimpleDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public FreelancerDto save(@Valid
                              @RequestBody
                              FreelancerSaveForm form) {
        return service.save(form);
    }

    @PutMapping
    public void update(@Valid
                       @RequestBody
                       FreelancerUpdateForm form) {
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")
                       Long id) {
        service.delete(id);
    }
}