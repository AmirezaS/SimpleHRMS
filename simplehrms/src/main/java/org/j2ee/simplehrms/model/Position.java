package org.j2ee.simplehrms.model;

import java.math.BigDecimal;
import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="positions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Position {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable=false, length=100)
    private String title;

    @Column(name="description", length=500)
    private String description;

    @Column(name="min_salary", precision=12, scale=2)
    private BigDecimal minSalary;

    @Column(name="max_salary", precision=12, scale=2)
    private BigDecimal maxSalary;

    @Enumerated(EnumType.STRING)
    @Column(name="grade", length=10)
    private SalaryGrade grade;

    @Column(name="is_active")
    @Builder.Default
    private Boolean isActive=true;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="department_id", nullable=false)
    private Department department;

    @OneToMany(mappedBy="position", fetch=FetchType.LAZY)
    @Builder.Default
    private List<Employee> employees = new ArrayList<>();
    

    public enum SalaryGrade{
        JUNIOR,
        MID,
        SENIOR,
        LEAD,
        MANAGER,
        DIRECTOR,
        VP,
        C_LEVEL
    }

}
