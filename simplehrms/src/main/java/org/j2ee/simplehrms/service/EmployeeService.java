package org.j2ee.simplehrms.service;

import org.j2ee.simplehrms.dao.EmployeeRepository;
import org.j2ee.simplehrms.dao.UserRepository;
import org.j2ee.simplehrms.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    // public EmployeeDTO.Response createEmployee(EmployeeDTO.CreateRequest request){
    //     //validate uniquness
    //     if (employeeRepository.existsByEmail(request.getEmail())){
    //         throw new IllegalStateException("Email already exists: " + request.getEmail());
    //     }

        
    // }
    



}
