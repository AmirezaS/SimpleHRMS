package org.j2ee.simplehrms.service;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.j2ee.simplehrms.dao.DepartmentReository;
import org.j2ee.simplehrms.dao.EmployeeRepository;
import org.j2ee.simplehrms.dao.PositionRepository;
import org.j2ee.simplehrms.dao.UserRepository;
import org.j2ee.simplehrms.dto.EmployeeDTO;
import org.j2ee.simplehrms.model.Department;
import org.j2ee.simplehrms.model.Employee;
import org.j2ee.simplehrms.model.Position;
import org.j2ee.simplehrms.model.User;
import org.j2ee.simplehrms.model.Employee.EmploymentStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentReository departmentReository;
    private final PositionRepository positionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    

    @Transactional
    public EmployeeDTO.Response createEmployee(EmployeeDTO.CreateRequest request){
        //validate uniquness
        if (employeeRepository.existsByEmail(request.getEmail())){
            throw new IllegalStateException("Email already exists: " + request.getEmail());
        }

        if (userRepository.existsByUsername(request.getUsername())){
            throw new IllegalStateException("Username already taken: "+ request.getUsername());
        }

        Department department = departmentReository.findById(request.getDepartmentId())
            .orElseThrow();
            
        Position position = positionRepository.findById(request.getPositionId())
            .orElseThrow();

        Employee manager = null;
        if (request.getManagerId() != null){
            manager = employeeRepository.findById(request.getManagerId())
                .orElseThrow();
        }

        Employee employee = Employee.builder()
            .employeeCode(generateEmployeeCode())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .nationalId(request.getNationalId())
            .dateOfBirth(request.getDateOfBirth())
            .gender(request.getGender())
            .address(request.getAddress())
            .hireDate(request.getHireDate())
            .baseSalary(request.getBaseSalary())
            .employmentType(request.getEmploymentType() != null ? request.getEmploymentType() : Employee.EmploymentType.FULL_TIME)
            .employmentStatus(EmploymentStatus.ACTIVE)
            .department(department)
            .position(position)
            .manager(manager)
            .emergencyContactName(request.getEmergencyContactName())
            .emergencyContactPhone(request.getEmergencyContactPhone())
            .build();

        employee = employeeRepository.save(employee);

        Set<User.Role> roles = new HashSet<>();
        if (request.getRoles() != null && !request.getRoles().isEmpty()){
            request.getRoles().forEach(r -> {
                try {roles.add(User.Role.valueOf(r));}
                catch(IllegalArgumentException e){
                    log.warn("Unknown role: {}", r);
                }
            });
        }
        if (roles.isEmpty()) roles.add(User.Role.ROLE_EMPLOYEE);
        
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .roles(roles)
            .employee(employee)
            .build();

        userRepository.save(user);

        log.info("Employee created: {} ({})", employee.getFullName(), employee.getEmployeeCode());
        
        return toResponse(employee);
        
    }

    //Helper methods
    public String generateEmployeeCode(){
            String prefix = "EMP-" + DateTimeFormatter.ofPattern("yy").format(LocalDate.now()) + "-";
            long count = employeeRepository.count() + 1;
            return prefix + String.format("%04d", count);
    }


    public EmployeeDTO.Response toResponse(Employee e) {
        return EmployeeDTO.Response.builder()
            .id(e.getId())
            .employeeCode(e.getEmployeeCode())
            .firstName(e.getFirstName())
            .lastName(e.getLastName())
            .fullName(e.getFullName())
            .email(e.getEmail())
            .phone(e.getPhone())
            .dateOfBirth(e.getDateOfBirth())
            .gender(e.getGender())
            .address(e.getAddress())
            .hireDate(e.getHireDate())
            .baseSalary(e.getBaseSalary())
            .employmentType(e.getEmploymentType())
            .status(e.getEmploymentStatus())
            .departmentId(e.getDepartment() != null ? e.getDepartment().getId() : null)
            .departmentName(e.getDepartment() != null ? e.getDepartment().getName() : null)
            .positionId(e.getPosition() != null ? e.getPosition().getId() : null)
            .positionTitle(e.getPosition() != null ? e.getPosition().getTitle() : null)
            .managerId(e.getManager() != null ? e.getManager().getId() : null)
            .managerName(e.getManager() != null ? e.getManager().getFullName() : null)
            .profileImageUrl(e.getProfileImageUrl())
            .emergencyContactName(e.getEmergencyContactName())
            .emergencyContactPhone(e.getEmergencyContactPhone())
            .build();
    }
    



}
