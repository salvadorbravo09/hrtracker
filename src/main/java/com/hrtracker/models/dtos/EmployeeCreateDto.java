package com.hrtracker.models.dtos;

import com.hrtracker.models.entities.Department;
import lombok.Data;

@Data
public class EmployeeCreateDto {

    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private Double salary;
    private Department department;
}
