package com.hrtracker.models.dtos;

import com.hrtracker.models.entities.Department;
import lombok.Data;

@Data
public class EmployeeDto {

    private Long id;
    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private Double salary;
    private DepartmentSimpleDto department;
}
