package com.hrtracker.services;

import com.hrtracker.models.dtos.DepartmentCreateDto;
import com.hrtracker.models.dtos.DepartmentDto;
import com.hrtracker.models.dtos.DepartmentSimpleDto;
import com.hrtracker.models.entities.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<DepartmentSimpleDto> getAllDepartment();
    Optional<DepartmentSimpleDto> getDepartmentById(Long id);
    DepartmentSimpleDto createDepartment(DepartmentCreateDto department);
    DepartmentSimpleDto updateDepartment(Long id, DepartmentCreateDto department);
    void deleteDepartment(Long id);
}
