package org.example.controller.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class QualificationUpdateForm {
    @NotNull(message = "Id can't be null!")
    private Long id;

    @NotNull(message = "Name can't be null!")
    @NotBlank(message = "Name can't be blank!")
    private String qualificationName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }
}
