package com.hrtracker.mapper;

import com.hrtracker.models.dtos.DepartmentCreateDto;
import com.hrtracker.models.dtos.DepartmentSimpleDto;
import com.hrtracker.models.entities.Department;

public class DepartmentMapper {
    public static Department convertCreateDtoToEntity(DepartmentCreateDto createDto) {
        Department department = new Department();
        department.setName(createDto.getName());
        return department;
    }

    public static DepartmentSimpleDto convertEntityToSimpleDto(Department department) {
        if (department == null) {
            return null;
        }
        return new DepartmentSimpleDto(department.getId(), department.getName());
    }
}
