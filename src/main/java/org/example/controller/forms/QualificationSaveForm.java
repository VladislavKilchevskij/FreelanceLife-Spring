package org.example.controller.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class QualificationSaveForm {
    @NotNull(message = "Name can't be null!")
    @NotBlank(message = "Name can't be blank!")
    private String qualificationName;

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }
}
