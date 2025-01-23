package com.adnanafzalbajwa.springbootJpaDemo.contoller;

import com.adnanafzalbajwa.springbootJpaDemo.dto.EmployeeDto;
import com.adnanafzalbajwa.springbootJpaDemo.model.Employee;
import com.adnanafzalbajwa.springbootJpaDemo.request.CreateEmployeeRequest;
import com.adnanafzalbajwa.springbootJpaDemo.response.CreateEmployeeResponse;
import com.adnanafzalbajwa.springbootJpaDemo.service.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public CreateEmployeeResponse createEmployee(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest) {
        EmployeeDto employeeDto = new ModelMapper().map(createEmployeeRequest, EmployeeDto.class);
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        return new ModelMapper().map(createdEmployee, CreateEmployeeResponse.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        Optional<Employee> fetchedEmployee = employeeService.getEmployee(id);
        if (fetchedEmployee.isPresent()) {
            return ResponseEntity.ok(fetchedEmployee.get());
        } else {
            return (ResponseEntity<Employee>) ResponseEntity.notFound();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        Employee updatedEmployee = employeeService.updateEmployee(employee.get(), employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

}
