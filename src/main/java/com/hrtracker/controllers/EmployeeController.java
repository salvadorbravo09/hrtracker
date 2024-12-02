package com.hrtracker.controllers;

import com.hrtracker.models.dtos.EmployeeCreateDto;
import com.hrtracker.models.dtos.EmployeeDto;
import com.hrtracker.models.entities.Employee;
import com.hrtracker.services.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
@Tag(name = "Employee", description = "Operations related to employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @GetMapping("/page/{page}")
    public Page<Employee> listPageable(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 8);
        return employeeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(employee -> ResponseEntity.status(HttpStatus.OK).body(employee))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeCreateDto employeeCreateDto) {
        EmployeeDto newEmployee = employeeService.createEmployee(employeeCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@Valid @PathVariable Long id, @RequestBody Employee employee) {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(id, employee);
        if (updatedEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        Optional<EmployeeDto> optionalEmployee = employeeService.getEmployeeById(id);
        if (optionalEmployee.isPresent()) {
            employeeService.deleteEmployee(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
