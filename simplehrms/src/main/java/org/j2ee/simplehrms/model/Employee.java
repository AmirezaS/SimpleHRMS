package org.j2ee.simplehrms.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="employees")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="employee_code", unique=true, nullable=false,length=20)
    private String employeeCode;

    @Column(name="first_name", nullable=false, length=100)
    private String firstName;

    @Column(name="last_name", nullable=false, length=100)
    private String lastName;

    @Column(name="email", nullable=false,unique=true,length=150)
    private String email;

    @Column(name="phone", length=20)
    private String phone;

    @Column(name="nationalId", length =30)
    private String nationalId;

    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name="gender", length=10)
    private Gender gender;

    @Column(name="address", length=300)
    private String address;

    @Column(name="hire_date", nullable=false)
    private LocalDate hireDate;

    @Column(name="termination_date")
    private LocalDate terminationDate;

    @Column(name="base_salary", precision=12, scale=2)
    private BigDecimal baseSalary;

    @Column(name="employment_type", length=20)
    @Builder.Default
    private EmploymentType employmentType = EmploymentType.FULL_TIME;


    @Enumerated(EnumType.STRING)
    @Column(name="status", length=20)
    @Builder.Default
    private EmploymentStatus employmentStatus = EmploymentStatus.ACTIVE;

    @Column(name="profile_image_url", length=500)
    private String ProfileImageUrl;

    @Column(name="emergency_contact_name", length=100)
    private String emergencyContactName;

    @Column(name="emergency_contact_phone", length=20)
    private String emergencyContactPhone;


    //Relatonships

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="department_id")
    private Department department;
    

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="position_id")
    private Position position;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private Employee manager;

    @OneToMany(mappedBy="manager", fetch=FetchType.LAZY)
    @Builder.Default
    private List<Employee> subordinates = new ArrayList<>();

    @OneToOne(mappedBy="employee", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy="employee", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @Builder.Default
    private List<Payroll> payrolls = new ArrayList<>();

    @OneToMany(mappedBy="employee", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @Builder.Default
    private List<Attendance> attendances = new ArrayList<>();

    @OneToMany(mappedBy="employee", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @Builder.Default
    private List<LeaveRequest> leaveRequests = new ArrayList<>();

    @OneToMany(mappedBy="employee", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @Builder.Default
    private List<PerformanceReview> performanceReviews = new ArrayList<>();


    public String getFullName() {
        return firstName + " " + lastName;
    }

    public enum Gender {MALE, FEMALE}

    public enum EmploymentType {FULL_TIME, PART_TIME, CONTRACT, INTERN, FREELANCE}

    public enum EmploymentStatus {ACTIVE, INACTIVE, ON_LEAVE, TERMINATED, PROBATION}

}
