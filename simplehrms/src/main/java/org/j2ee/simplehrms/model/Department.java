package org.j2ee.simplehrms.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Column(name="name", nullable=false, unique=true, length=100)
    private String name;

    @Column(name="code", unique=true, length=10)
    private String code;

    @Column(name="description", length=500)
    private String description;

    @Column(name="budget", precision=15, scale=2)
    private BigDecimal budget;

    @Column(name="location", length=100)
    private String location;

    @Column(name="is_active")
    @Builder.Default
    private Boolean isActive = true;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private Employee manager;

    @OneToMany(mappedBy="dpartment", fetch=FetchType.LAZY)
    @Builder.Default
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy="department", fetch=FetchType.LAZY)
    @Builder.Default
    private List<Position> positions = new ArrayList<>();

}
