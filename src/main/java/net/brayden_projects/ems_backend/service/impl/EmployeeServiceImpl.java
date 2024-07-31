package net.brayden_projects.ems_backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.brayden_projects.ems_backend.dto.EmployeeDTO;
import net.brayden_projects.ems_backend.entity.Employee;
import net.brayden_projects.ems_backend.exception.ResourceNotFoundException;
import net.brayden_projects.ems_backend.repository.EmployeeRepository;
import net.brayden_projects.ems_backend.service.EmployeeService;
import net.brayden_projects.mapper.EmployeeMapper;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee does not exis twith given id: " + employeeId));
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDTO(updatedEmployee);
    }

    @Override
    public EmployeeDTO findEmployeeById(Long id)
    {
        Employee foundEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with given id : " + id));
        return EmployeeMapper.mapToEmployeeDTO(foundEmployee);    
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Employee foundEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with given id : " + id));
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeesDTO = new ArrayList<EmployeeDTO>();

        for(Employee employee : employees)
        {
            employeesDTO.add(EmployeeMapper.mapToEmployeeDTO(employee));
        }

        return employeesDTO;

        //We can also use stream to convert to a list
        //return employees.stream().map((employees) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    

}
