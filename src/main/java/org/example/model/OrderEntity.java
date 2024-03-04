package org.example.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "qualif_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String orderTitle;
    @Column
    private String orderDescription;
    @Column
    private BigDecimal orderPrice;
    @Column
    private LocalDate orderTerm;
    @ManyToOne
    @JoinColumn(name = "qualificationId")
    private QualificationEntity qualification;

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

    public QualificationEntity getQualification() {
        return qualification;
    }

    public void setQualification(QualificationEntity qualification) {
        this.qualification = qualification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id.equals(that.id) && orderTitle.equals(that.orderTitle) && orderDescription.equals(that.orderDescription) && orderPrice.equals(that.orderPrice) && orderTerm.equals(that.orderTerm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderTitle, orderDescription, orderPrice, orderTerm);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
               "id=" + id +
               ", orderTitle='" + orderTitle + '\'' +
               ", orderDescription='" + orderDescription + '\'' +
               ", orderPrice=" + orderPrice +
               ", orderTerm=" + orderTerm +
               '}';
    }
}