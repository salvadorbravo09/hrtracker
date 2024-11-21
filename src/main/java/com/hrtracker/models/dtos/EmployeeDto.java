package com.hrtracker.models.dtos;

import com.hrtracker.models.entities.Department;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeDto {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Min(value = 18, message = "Age must be at least 18 years old")
    @Max(value = 100, message = "Age must be less than 100 years old")
    private Integer age;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
    private Double salary;

    private DepartmentSimpleDto department;
}
