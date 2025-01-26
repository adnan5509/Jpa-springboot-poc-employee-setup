package com.adnanafzalbajwa.springbootJpaDemo.contoller;

import com.adnanafzalbajwa.springbootJpaDemo.dto.EmployeeDto;
import com.adnanafzalbajwa.springbootJpaDemo.model.Employee;
import com.adnanafzalbajwa.springbootJpaDemo.request.CreateEmployeeRequest;
import com.adnanafzalbajwa.springbootJpaDemo.response.CreateEmployeeResponse;
import com.adnanafzalbajwa.springbootJpaDemo.response.GetEmployeeResponse;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public GetEmployeeResponse getEmployee(@PathVariable int id) {
        Optional<Employee> fetchedEmployee = employeeService.getEmployee(id);
        if (fetchedEmployee.isPresent()) {
            EmployeeDto employeeDto = new ModelMapper().map(fetchedEmployee, EmployeeDto.class);
            return new ModelMapper().map(employeeDto, GetEmployeeResponse.class);
        } else {
            return (GetEmployeeResponse) ResponseEntity.notFound();
        }
    }

    @GetMapping
    public List<GetEmployeeResponse> getAllEmployee() {
        List<Employee> fetchedEmployees = employeeService.getAllEmployees();
        ModelMapper modelMapper = new ModelMapper();
        List<GetEmployeeResponse> employeeResponseList = fetchedEmployees
                .stream()
                .map(employee -> modelMapper.map(employee, GetEmployeeResponse.class))
                .collect(Collectors.toList());
        return employeeResponseList;
    }


    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        Employee updatedEmployee = employeeService.updateEmployee(employee.get(), employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

}
