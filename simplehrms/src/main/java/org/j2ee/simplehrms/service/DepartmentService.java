package org.j2ee.simplehrms.service;

import org.j2ee.simplehrms.dao.DepartmentReository;
import org.j2ee.simplehrms.dao.EmployeeRepository;
import org.j2ee.simplehrms.dto.DepartmentDTO;
import org.j2ee.simplehrms.model.Department;
import org.j2ee.simplehrms.model.Employee;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {
    private final DepartmentReository departmentReository;
    private final EmployeeRepository employeeRepository;

    public DepartmentDTO.Response create(DepartmentDTO.CreateRequest request){
        if (departmentReository.existsByName(request.getName())){
            throw new IllegalStateException("Department name already exists: " + request.getName());
        }
        if (departmentReository.existsByCode(request.getCode())){
            throw new IllegalStateException("Department code already exists: " + request.getCode());
        }

        Employee manager = null;
        if (request.getManagerId() != null){
            manager = employeeRepository.findById(request.getManagerId())
                .orElseThrow();
        }

        Department dept = Department.builder()
            .name(request.getName())
            .code(request.getCode().toUpperCase())
            .description(request.getDescription())
            .budget(request.getBudget())
            .location(request.getLocation())
            .manager(manager)
            .build();

        return toResponse(departmentReository.save(dept));
    }

    private DepartmentDTO.Response toResponse(Department d) {
        return DepartmentDTO.Response.builder()
            .id(d.getId())
            .name(d.getName())
            .code(d.getCode())
            .description(d.getDescription())
            .budget(d.getBudget())
            .location(d.getLocation())
            .isActive(d.getIsActive())
            .managerId(d.getManager() != null ? d.getManager().getId() : null)
            .managerName(d.getManager() != null ? d.getManager().getFullName() : null)
            // .employeeCount(employeeRepository.countActiveByDepartment(d.getId()))
            .build();
    }

}
