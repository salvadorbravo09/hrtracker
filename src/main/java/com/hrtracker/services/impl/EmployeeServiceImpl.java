package com.hrtracker.services.impl;

import com.hrtracker.mapper.EmployeeMapper;
import com.hrtracker.models.dtos.EmployeeCreateDto;
import com.hrtracker.models.dtos.EmployeeDto;
import com.hrtracker.models.entities.Employee;
import com.hrtracker.repositories.DepartmentRepository;
import com.hrtracker.repositories.EmployeeRepository;
import com.hrtracker.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> EmployeeMapper.convertEntityToDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return this.employeeRepository.findAll(pageable);
    }

    @Override
    public Optional<EmployeeDto> getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(employee -> EmployeeMapper.convertEntityToDto(employee));
    }

    @Override
    public EmployeeDto createEmployee(EmployeeCreateDto employeeCreateDto) {
        EmployeeMapper mapper = new EmployeeMapper(departmentRepository);

        Employee newEmployee = EmployeeMapper.convertCreateDtoToEntity(employeeCreateDto);

        Employee savedEmployee = employeeRepository.save(newEmployee);
        return EmployeeMapper.convertEntityToDto(savedEmployee);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            employee.setId(id);
            Employee updatedEmployee = employeeRepository.save(employee);
            return EmployeeMapper.convertEntityToDto(updatedEmployee);
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
