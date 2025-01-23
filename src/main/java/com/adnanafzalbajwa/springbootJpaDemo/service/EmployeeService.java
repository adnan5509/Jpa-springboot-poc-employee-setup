package com.adnanafzalbajwa.springbootJpaDemo.service;

import com.adnanafzalbajwa.springbootJpaDemo.dto.EmployeeDto;
import com.adnanafzalbajwa.springbootJpaDemo.exception.EmployeeServiceException;
import com.adnanafzalbajwa.springbootJpaDemo.model.Employee;
import com.adnanafzalbajwa.springbootJpaDemo.repository.EmployeeRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public EmployeeDto createEmployee(final EmployeeDto employeeDto) {

        if (employeeRepository.findByEmail(employeeDto.getEmail()) != null) {
            throw new EmployeeServiceException("Record already exists");
        }

        Employee employee = new ModelMapper().map(employeeDto, Employee.class);

        EmployeeDto returnEmployeeDto = new ModelMapper().map(employeeRepository.save(employee), EmployeeDto.class);
        return returnEmployeeDto;
    }

    public Optional getEmployee(final int id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public Employee updateEmployee(final Employee employee, final Employee employeeDetails) {
        employee.setName(employeeDetails.getName());
        employee.setAge(employeeDetails.getAge());
        return employeeRepository.save(employee);
    }
}
