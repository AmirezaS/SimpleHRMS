package org.j2ee.simplehrms.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "attendences")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
 
    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendanceDate;
 
    @Column(name = "check_in_time")
    private LocalTime checkInTime;
 
    @Column(name = "check_out_time")
    private LocalTime checkOutTime;
 
    @Column(name = "working_hours", precision = 4, scale = 2)
    private BigDecimal workingHours;
 
    @Column(name = "overtime_hours", precision = 4, scale = 2)
    @Builder.Default
    private BigDecimal overtimeHours = BigDecimal.ZERO;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    @Builder.Default
    private AttendanceStatus status = AttendanceStatus.PRESENT;
 
    @Column(name = "notes", length = 300)
    private String notes;
 
    @Column(name = "is_remote")
    @Builder.Default
    private Boolean isRemote = false;
 
    @Column(name = "location", length = 100)
    private String location;
 
    public enum AttendanceStatus {
        PRESENT, ABSENT, LATE, HALF_DAY, HOLIDAY, WEEKEND, ON_LEAVE, WORK_FROM_HOME
    }
}
