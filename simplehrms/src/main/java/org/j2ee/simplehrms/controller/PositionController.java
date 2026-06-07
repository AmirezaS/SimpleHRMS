package org.j2ee.simplehrms.controller;

import java.math.BigDecimal;

import org.j2ee.simplehrms.dao.DepartmentReository;
import org.j2ee.simplehrms.dao.PositionRepository;
import org.j2ee.simplehrms.model.Department;
import org.j2ee.simplehrms.model.Position;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//base URL: /api/positions

//A "position" is a job title within a department.
//Ex: "Software Engineer" in Engineering, "HR Manager" in HR.

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionRepository positionRepository;
    private final DepartmentReository departmentReository;

    // CREATE 
    // POST /api/positions

    public ResponseEntity<PositionResponse> create(
        @Valid @RequestBody CreatePositionRequest request
    ){
        Department department = departmentReository.findById(request.getDepartmentId())
            .orElseThrow();
        
        if (positionRepository.existsByTitleAndDepartmentIdd(request.getTitle(), request.getDepartmentId())) {
            throw new IllegalStateException();
        }

        Position position = Position.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .grade(request.getGrade())
            .minSalary(request.getMinSalary())
            .maxSalary(request.getMaxSalary())
            .department(department)
            .build();

        position = positionRepository.save(position);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(toResponse(position));
    }

    

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreatePositionRequest {

        @NotBlank(message = "Thitle is required")
        @Size(max = 100)
        private String title;

        @Size(max = 500)
        private String description;

        private Position.SalaryGrade grade;

        @Positive(message = "Min salary must be positive")
        private BigDecimal minSalary;

        @Positive(message = "Max Salary must be positive")
        private BigDecimal maxSalary;

        @NotNull(message = "Department ID is required")
        private Long departmentId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PositionResponse {
        private Long id;
        private String title;
        private String description;
        private Position.SalaryGrade grade;
        private BigDecimal minSalary;
        private BigDecimal maxSalary;
        private Boolean isActive;
        private Long departmentId;
        private String departmentName;
    
        
    }

    private PositionResponse toResponse(Position p){
        return PositionResponse.builder()
            .id(p.getId())
            .title(p.getTitle())
            .description(p.getDescription())
            .grade(p.getGrade())
            .minSalary(p.getMinSalary())
            .maxSalary(p.getMaxSalary())
            .isActive(p.getIsActive())
            .departmentId(p.getDepartment().getId())
            .departmentName(p.getDepartment().getName())
            .build();

    }

}
