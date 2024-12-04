package com.hrtracker.controllers;

import com.hrtracker.models.dtos.DepartmentCreateDto;
import com.hrtracker.models.dtos.DepartmentSimpleDto;
import com.hrtracker.services.DepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
@Tag(name = "Department", description = "Operations related to departments")
@PreAuthorize("authenticated()")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<DepartmentSimpleDto>> findAllDepartments() {
        List<DepartmentSimpleDto> departments = departmentService.getAllDepartment();
        if (departments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(departments);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<DepartmentSimpleDto> findDepartmentById(@PathVariable Long id) {
        Optional<DepartmentSimpleDto> departmentSimpleDto = departmentService.getDepartmentById(id);
        return departmentSimpleDto
                .map(dep -> ResponseEntity.status(HttpStatus.OK).body(dep))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<DepartmentSimpleDto> createDepartment(@RequestBody DepartmentCreateDto department) {
        DepartmentSimpleDto newDepartment = departmentService.createDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDepartment);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<DepartmentSimpleDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentCreateDto department) {
        DepartmentSimpleDto updatedDepartment = departmentService.updateDepartment(id, department);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
