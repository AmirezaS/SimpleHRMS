package org.j2ee.simplehrms.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.j2ee.simplehrms.model.Employee;
import org.j2ee.simplehrms.model.Employee.EmploymentStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;


public class EmployeeDTO {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest{

        @NotBlank(message="First Name is Required")
        @Size(max = 100)
        private String firstName;

        @NotBlank(message="Last Name is Required")
        @Size(max = 100)
        private String lastName;


        @NotBlank
        @Email(message = "Valid email is Required")
        private String email; 

        @Pattern(regexp="^[+]?[0-9]{7,15}$", message="Invalid phone number")
        private String phone;

        private String nationalId;

        @Past(message="Date of birth must be in the past")
        private LocalDate dateOfBirth;

        private Employee.Gender gender;

        private String address;

        @NotNull(message="Hire Date is required")
        private LocalDate hireDate;

        @NotNull(message="Base salary is Required")
        @Positive(message = "Salary must be positive")
        private BigDecimal baseSalary;

        private Employee.EmploymentType employmentType;

        @NotNull(message = "Department is Required")
        private Long departmentId;

        @NotNull(message= "Position is Required")
        private Long positionId;

        private Long managerId;
        private String emergencyContactName;
        private String emergencyContactPhone;

        @NotBlank(message="Username is Required")
        private String username;


        @NotBlank(message = "Password is Required")
        @Size(min = 8, message="Password must be at least 8 characters")
        private String password;

        private Set<String> roles;


    }


    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class UpdateRequest {
        @Size(max = 100) private String firstName;
        @Size(max = 100) private String lastName;
        @Email private String email;
        private String phone;
        private String address;
        private BigDecimal baseSalary;
        private Employee.EmploymentType employmentType;
        private EmploymentStatus status;
        private Long departmentId;
        private Long positionId;
        private Long managerId;
        private String emergencyContactName;
        private String emergencyContactPhone;
    }
 
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private Long id;
        private String employeeCode;
        private String firstName;
        private String lastName;
        private String fullName;
        private String email;
        private String phone;
        private LocalDate dateOfBirth;
        private Employee.Gender gender;
        private String address;
        private LocalDate hireDate;
        private BigDecimal baseSalary;
        private Employee.EmploymentType employmentType;
        private EmploymentStatus status;
        private Long departmentId;
        private String departmentName;
        private Long positionId;
        private String positionTitle;
        private Long managerId;
        private String managerName;
        private String profileImageUrl;
        private String emergencyContactName;
        private String emergencyContactPhone;
        private LocalDateTime createdAt;
    }
 
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class Summary {
        private Long id;
        private String employeeCode;
        private String fullName;
        private String email;
        private String departmentName;
        private String positionTitle;
        private EmploymentStatus status;
    }

}
