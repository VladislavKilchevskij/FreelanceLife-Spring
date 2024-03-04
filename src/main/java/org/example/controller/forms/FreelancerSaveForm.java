package org.example.controller.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.service.dto.QualificationSimpleDto;

import java.util.List;

public class FreelancerSaveForm {
    @NotNull(message = "Name can't be null!")
    @NotBlank(message = "Name can't be blank!")
    private String freelancerName;

    @NotNull(message = "Second name can't be null!")
    @NotBlank(message = "Second name can't be blank!")
    private String freelancerSecondName;

    @NotNull(message = "Email can't be null!")
    @NotBlank(message = "Email can't be blank!")
    @Pattern(regexp = "^[\\w-.]+@([\\w-.])+[\\w-]{2,4}$", message = "Email must contain '@' and ends with domain ('.com', '.ru', etc.).")
    private String freelancerEmail;

    @NotNull(message = "At least 1 qualification must be specified!")
    private List<QualificationSimpleDto> qualifications;

    public String getFreelancerName() {
        return freelancerName;
    }

    public void setFreelancerName(String freelancerName) {
        this.freelancerName = freelancerName;
    }

    public String getFreelancerSecondName() {
        return freelancerSecondName;
    }

    public void setFreelancerSecondName(String freelancerSecondName) {
        this.freelancerSecondName = freelancerSecondName;
    }

    public String getFreelancerEmail() {
        return freelancerEmail;
    }

    public void setFreelancerEmail(String freelancerEmail) {
        this.freelancerEmail = freelancerEmail;
    }

    public List<QualificationSimpleDto> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<QualificationSimpleDto> qualifications) {
        this.qualifications = qualifications;
    }
}