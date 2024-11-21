package com.hrtracker.models.dtos;

import com.hrtracker.models.entities.Employee;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentDto {

    private Long id;
    private String name;
    private List<Employee> employees;
}
