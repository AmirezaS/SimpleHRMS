package org.j2ee.simplehrms.controller;

import org.j2ee.simplehrms.dto.EmployeeDTO;
import org.j2ee.simplehrms.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    //Spring automatically injects EmployeeService here
    private final EmployeeService employeeService;

    // CREATE
    // HTTP method: POST
    // URL: /api/employees
    // Body: JSON with employee details 
    // returnes: 201 created + the saved employee as JSON

    public ResponseEntity<EmployeeDTO.Response> create(
        @Valid @RequestBody EmployeeDTO.CreateRequest request
    ){
        EmployeeDTO.Response saved = employeeService.createEmployee(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
