package com.hrtracker.controllers;

import com.hrtracker.models.dtos.DepartmentCreateDto;
import com.hrtracker.models.dtos.DepartmentDto;
import com.hrtracker.models.dtos.DepartmentSimpleDto;
import com.hrtracker.models.entities.Department;
import com.hrtracker.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentSimpleDto>> findAllDepartments() {
        List<DepartmentSimpleDto> departments = departmentService.getAllDepartment();
        if (departments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentSimpleDto> findDepartmentById(@PathVariable Long id) {
        Optional<DepartmentSimpleDto> departmentSimpleDto = departmentService.getDepartmentById(id);
        return departmentSimpleDto
                .map(dep -> ResponseEntity.status(HttpStatus.OK).body(dep))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<DepartmentSimpleDto> createDepartment(@RequestBody DepartmentCreateDto department) {
        DepartmentSimpleDto newDepartment = departmentService.createDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDepartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentSimpleDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentCreateDto department) {
        DepartmentSimpleDto updatedDepartment = departmentService.updateDepartment(id, department);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
