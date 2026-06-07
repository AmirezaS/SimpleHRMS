package org.j2ee.simplehrms.model;

import jakarta.persistence.*;
import lombok.*;
 
@Entity
@Table(name = "leave_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveType {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;
 
    @Column(name = "description", length = 300)
    private String description;
 
    @Column(name = "max_days_per_year", nullable = false)
    private Integer maxDaysPerYear;
 
    @Column(name = "is_paid")
    @Builder.Default
    private Boolean isPaid = true;
 
    @Column(name = "requires_approval")
    @Builder.Default
    private Boolean requiresApproval = true;
 
    @Column(name = "can_carry_forward")
    @Builder.Default
    private Boolean canCarryForward = false;
 
    @Column(name = "max_carry_forward_days")
    @Builder.Default
    private Integer maxCarryForwardDays = 0;
 
    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;
}
