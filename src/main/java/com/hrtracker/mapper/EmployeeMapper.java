package com.hrtracker.mapper;

import com.hrtracker.models.dtos.DepartmentSimpleDto;
import com.hrtracker.models.dtos.EmployeeCreateDto;
import com.hrtracker.models.dtos.EmployeeDto;
import com.hrtracker.models.entities.Department;
import com.hrtracker.models.entities.Employee;
import com.hrtracker.repositories.DepartmentRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeMapper {

    private static DepartmentRepository departmentRepository;

    public EmployeeMapper(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public static EmployeeDto convertEntityToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setAge(employee.getAge());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setSalary(employee.getSalary());

        if (employee.getDepartment() != null) {
            employeeDto.setDepartment(
                    new DepartmentSimpleDto(
                            employee.getDepartment().getId(),
                            employee.getDepartment().getName()
                    )
            );
        }
        return employeeDto;
    }

    public static Employee convertCreateDtoToEntity(EmployeeCreateDto createDto) {
        Employee employee = new Employee();
        employee.setName(createDto.getName());
        employee.setLastName(createDto.getLastName());
        employee.setAge(createDto.getAge());
        employee.setEmail(createDto.getEmail());
        employee.setSalary(createDto.getSalary());

        // If department ID is provided, create a minimal department
        if (createDto.getDepartment() != null && createDto.getDepartment().getId() != null) {
            Department department = departmentRepository.findById(createDto.getDepartment().getId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + createDto.getDepartment().getId()));
            employee.setDepartment(department);
        }
        return employee;
    }
}
