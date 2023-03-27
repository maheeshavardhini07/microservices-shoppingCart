package com.microservice.departmentservice.service;

import java.util.List;

import com.microservice.departmentservice.entity.Department;

public interface DepartmentService {
    
    Department saveDepartment(Department department);

    Department getDepartmentById(Long departmentId);

    List<Department> getAllDept();
}
