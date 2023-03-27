package com.microservice.departmentservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.departmentservice.entity.Department;
import com.microservice.departmentservice.repository.DepartmentRepository;
// import com.microservice.departmentservice.service.DepartmentService;
// import com.microservice.departmentservice.service.DepartmentServiceImpl;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    
    // private DepartmentServiceImpl departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department){
        Department savedDepartment = departmentRepository.save(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Department>> getDepartmentById(@PathVariable("id") Long departmentId){
        Optional<Department> department = departmentRepository.findById(departmentId);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAllDept(){
        List<Department> department = departmentRepository.findAll();
        return ResponseEntity.ok(department);
    }
}
