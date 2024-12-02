package com.hrtracker.services;

import com.hrtracker.models.dtos.EmployeeCreateDto;
import com.hrtracker.models.dtos.EmployeeDto;
import com.hrtracker.models.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();
    Page<Employee> findAll(Pageable pageable);
    Optional<EmployeeDto> getEmployeeById(Long id);
    EmployeeDto createEmployee(EmployeeCreateDto employee);
    EmployeeDto updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
}
