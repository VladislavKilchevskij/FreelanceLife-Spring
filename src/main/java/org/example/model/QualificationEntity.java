package org.example.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "qualification")
public class QualificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String qualificationName;
    @OneToMany(mappedBy = "qualification", fetch = FetchType.EAGER)
    private List<OrderEntity> orders;
    @ManyToMany(mappedBy = "qualifications", fetch = FetchType.EAGER)
    private List<FreelancerEntity> freelancers;

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

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public List<FreelancerEntity> getFreelancers() {
        return freelancers;
    }

    public void setFreelancers(List<FreelancerEntity> freelancers) {
        this.freelancers = freelancers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualificationEntity that = (QualificationEntity) o;
        return id.equals(that.id) && qualificationName.equals(that.qualificationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qualificationName);
    }

    @Override
    public String toString() {
        return "QualificationEntity{" +
                "id=" + id +
                ", qualificationName='" + qualificationName + '\'' +
                '}';
    }
}
