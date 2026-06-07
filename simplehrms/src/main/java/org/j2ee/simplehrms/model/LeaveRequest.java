package org.j2ee.simplehrms.model;

import jakarta.persistence.*;
import lombok.*;
 
import java.time.LocalDate;
import java.time.LocalDateTime;
 
@Entity
@Table(name = "leave_requests", indexes = {
    @Index(name = "idx_leave_employee", columnList = "employee_id"),
    @Index(name = "idx_leave_dates", columnList = "start_date, end_date")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequest {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_type_id", nullable = false)
    private LeaveType leaveType;
 
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
 
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
 
    @Column(name = "total_days", nullable = false)
    private Integer totalDays;
 
    @Column(name = "reason", nullable = false, length = 500)
    private String reason;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    @Builder.Default
    private LeaveStatus status = LeaveStatus.PENDING;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    private Employee approvedBy;
 
    @Column(name = "approved_at")
    private LocalDateTime approvedAt;
 
    @Column(name = "rejection_reason", length = 300)
    private String rejectionReason;
 
    @Column(name = "is_half_day")
    @Builder.Default
    private Boolean isHalfDay = false;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "half_day_period", length = 10)
    private HalfDayPeriod halfDayPeriod;
 
    public enum LeaveStatus { PENDING, APPROVED, REJECTED, CANCELLED }
 
    public enum HalfDayPeriod { MORNING, AFTERNOON }
}
