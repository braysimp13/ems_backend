package net.brayden_projects.ems_backend.service;

import java.util.List;

import net.brayden_projects.ems_backend.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDto);

    EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO);

    EmployeeDTO findEmployeeById(Long id);

    void deleteEmployeeById(Long id);

    List<EmployeeDTO> findAllEmployees();
}
