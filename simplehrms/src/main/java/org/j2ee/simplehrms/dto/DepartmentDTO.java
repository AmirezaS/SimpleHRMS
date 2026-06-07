package org.j2ee.simplehrms.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.j2ee.simplehrms.dto.EmployeeDTO.CreateRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DepartmentDTO {

    @Getter 
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
    
        @NotBlank(message = "Department name is required")
        @Size(max = 100)
        private String name;

        @NotBlank
        @Size(max=10, message = "Code must be max 10 characters")
        private String code;

        @Size(max=500)
        private String description;

        @Positive
        private BigDecimal budget;

        @Size(max=100)
        private String location;

        private Long managerId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class updateRequest {

        @Size(max = 100)
        private String name;

        @Size(max=500)
        private String description;

        private BigDecimal budget;

        @Size(max = 100)
        private String location;

        private Long managerId;

        private Boolean isActive;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String name;
        private String code;
        private String description;
        private BigDecimal budget;
        private String location;
        private Boolean isActive;
        private Long managerId;
        private String managerName;
        private Long employeeCount;
        private LocalDateTime createdAt;
    }

}
