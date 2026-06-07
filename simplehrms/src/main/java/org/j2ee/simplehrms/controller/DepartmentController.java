package org.j2ee.simplehrms.controller;

import org.j2ee.simplehrms.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public ResponseEntity<

}
