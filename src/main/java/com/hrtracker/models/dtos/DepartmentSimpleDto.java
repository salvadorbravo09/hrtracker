package com.hrtracker.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentSimpleDto {

    private Long id;

    @NotBlank
    @Size(min = 50, message = "The name must not exceed 50 characters")
    private String name;
}
