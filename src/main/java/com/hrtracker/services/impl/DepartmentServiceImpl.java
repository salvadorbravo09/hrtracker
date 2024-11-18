package com.hrtracker.services.impl;

import com.hrtracker.models.entities.Department;
import com.hrtracker.repositories.DepartmentRepository;
import com.hrtracker.services.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartment() {
        return this.departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        Optional<Department> existingDepartmentOpt = departmentRepository.findById(id);
        if (existingDepartmentOpt.isEmpty()) {
            return null;
        }

        Department updatedDepartment = existingDepartmentOpt.get();
        updatedDepartment.setName(department.getName());
        return departmentRepository.save(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
