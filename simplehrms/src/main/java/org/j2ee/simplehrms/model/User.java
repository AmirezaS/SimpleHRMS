package org.j2ee.simplehrms.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.annotation.Generated;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable=false, unique=true, length=100)
    private String username;

    @Column(name="password", nullable=false)
    private String password;


    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="user_roles", joinColumns=@JoinColumn(name="user_id"))
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<Role> roles = new HashSet<>();


    @Column(name = "is_enabled")
    @Builder.Default
    private Boolean isEnabled = true;

    @Column(name="is_account_non_locked")
    @Builder.Default
    private Boolean isAccountNonLocked = true;


    @Column(name="last_login")
    private LocalDateTime lastLogin;

    @Column(name="refresh_token", length=500)
    private String refreshToken;

    @Column(name="refresh_token_expiry")
    private LocalDateTime refreshTokenExpiry;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="employee_id", unique=true)
    private Employee employee;

    public enum Role {
        ROLE_ADMIN,
        ROLE_HR_MANAGER,
        ROLE_EMPLOYEE,
        ROLE_PAYROLL_ADMIN,
        ROLE_DEPARTMENT_MANAGER
    }

    
}
