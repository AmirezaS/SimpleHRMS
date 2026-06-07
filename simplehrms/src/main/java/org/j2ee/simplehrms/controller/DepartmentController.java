package org.j2ee.simplehrms.controller;

import org.j2ee.simplehrms.dto.DepartmentDTO;
import org.j2ee.simplehrms.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// base URL: /api/departments
// an employee must have a department, so you need to create a department before you can create an employee


@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // CREATE 
    // POST /api/departments

    public ResponseEntity<DepartmentDTO.Response> create(
        @Valid @RequestBody DepartmentDTO.CreateRequest request
    ){
        DepartmentDTO.Response created = departmentService.create(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(created);
    }

}
