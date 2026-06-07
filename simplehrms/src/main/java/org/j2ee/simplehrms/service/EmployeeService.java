package org.j2ee.simplehrms.service;

import javax.swing.text.Position;

import org.j2ee.simplehrms.dao.DepartmentReository;
import org.j2ee.simplehrms.dao.EmployeeRepository;
import org.j2ee.simplehrms.dao.UserRepository;
import org.j2ee.simplehrms.dto.EmployeeDTO;
import org.j2ee.simplehrms.model.Department;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final DepartmentReository departmentReository;
    

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
            
        
    
         
        


        
    }
    



}
