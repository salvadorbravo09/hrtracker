package com.hrtracker.services.impl;

import com.hrtracker.mapper.DepartmentMapper;
import com.hrtracker.models.dtos.DepartmentCreateDto;
import com.hrtracker.models.dtos.DepartmentDto;
import com.hrtracker.models.dtos.DepartmentSimpleDto;
import com.hrtracker.models.entities.Department;
import com.hrtracker.repositories.DepartmentRepository;
import com.hrtracker.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentSimpleDto> getAllDepartment() {
        return departmentRepository.findAll()
                .stream()
                .map(department -> DepartmentMapper.convertEntityToSimpleDto(department))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DepartmentSimpleDto> getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .map(department -> DepartmentMapper.convertEntityToSimpleDto(department));
    }

    @Override
    public DepartmentSimpleDto createDepartment(DepartmentCreateDto departmentCreateDto) {
        Department newDepartment = DepartmentMapper.convertCreateDtoToEntity(departmentCreateDto);
        Department savedDepartment = departmentRepository.save(newDepartment);
        return DepartmentMapper.convertEntityToSimpleDto(savedDepartment);
    }

    @Override
    public DepartmentSimpleDto updateDepartment(Long id, DepartmentCreateDto departmentCreateDto) {
        Optional<Department> existingDepartment = departmentRepository.findById(id);

        if (existingDepartment.isPresent()) {
            Department departmentToUpdate = existingDepartment.get();
            departmentToUpdate.setName(departmentCreateDto.getName());

            Department updatedDepartment = departmentRepository.save(departmentToUpdate);
            return DepartmentMapper.convertEntityToSimpleDto(updatedDepartment);
        } else {
            throw new RuntimeException("Department not found with id: " + id);
        }
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
