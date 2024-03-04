package org.example.controller.forms;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.service.dto.QualificationSimpleDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderUpdateForm {
    @NotNull(message = "Id can't be null!")
    private Long id;

    @NotNull(message = "Title can't be null!")
    @NotBlank(message = "Title can't be blank!")
    private String orderTitle;

    @NotNull(message = "Description can't be null!")
    @NotBlank(message = "Description can't be blank!")
    private String orderDescription;

    @NotNull(message = "Price can't be null!")
    @Digits(message = "Price must contain from 0 to 10 digits before ',' and 2 after!", integer = 10, fraction = 2)
    private BigDecimal orderPrice;

    @NotNull(message = "Term can't be null!")
    private LocalDate orderTerm;

    @NotNull(message = "Qualification can't be null!")
    private QualificationSimpleDto qualification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public LocalDate getOrderTerm() {
        return orderTerm;
    }

    public void setOrderTerm(LocalDate orderTerm) {
        this.orderTerm = orderTerm;
    }

    public QualificationSimpleDto getQualification() {
        return qualification;
    }

    public void setQualification(QualificationSimpleDto qualification) {
        this.qualification = qualification;
    }
}
