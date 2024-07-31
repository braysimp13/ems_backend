package net.brayden_projects.ems_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.brayden_projects.ems_backend.dto.EmployeeDTO;
import net.brayden_projects.ems_backend.entity.Employee;
import net.brayden_projects.ems_backend.service.EmployeeService;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("employees/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    //Build Add employee REST API
    // POST https://localhost:8080/employees/api/employees/create
    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Update employee by id
    // PUT https://localhost:8080/employees/api/employees/update/{id}
    @PutMapping("update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable("id") Long employeeId)
    {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeId,employeeDTO);
        return new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
    }

    //Get a single employee by and id
    @GetMapping("/{id}")
    // GET https://localhost:8080/employees/api/employees/id
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDTO employee = employeeService.findEmployeeById(employeeId);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    //Delete an employee by id
    // DELETE https://localhost:8080/employees/api/employees/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployeeByID(@PathVariable("id") Long employeeId)
    {
        employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>("Employee has been deleted succesfully.",HttpStatus.OK);
    }
    
    //Get All Employees
    // GET https://localhost:8080/employees/api/employees
    @GetMapping() 
    public ResponseEntity<List<EmployeeDTO>> findAllEmployees()
    {
        return new ResponseEntity<>(employeeService.findAllEmployees(),HttpStatus.OK);
    }
    
}
