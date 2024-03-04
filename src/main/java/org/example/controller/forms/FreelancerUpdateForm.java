package org.example.controller.forms;

import jakarta.validation.constraints.*;
import org.example.service.dto.QualificationSimpleDto;

import java.util.List;
import java.util.Objects;
public class FreelancerUpdateForm {
    @NotNull(message = "Id can't be null!")
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreelancerUpdateForm that = (FreelancerUpdateForm) o;
        return id.equals(that.id) && freelancerName.equals(that.freelancerName) && freelancerSecondName.equals(that.freelancerSecondName) && freelancerEmail.equals(that.freelancerEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, freelancerName, freelancerSecondName, freelancerEmail);
    }
}
