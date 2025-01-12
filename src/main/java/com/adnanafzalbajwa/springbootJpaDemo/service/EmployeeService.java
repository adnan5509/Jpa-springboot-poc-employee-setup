package com.adnanafzalbajwa.springbootJpaDemo.service;

import com.adnanafzalbajwa.springbootJpaDemo.model.Employee;
import com.adnanafzalbajwa.springbootJpaDemo.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee createEmployee(final Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional getEmployee(final int id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public Employee updateEmployee(final Employee employee, final Employee employeeDetails) {
        employee.setName(employeeDetails.getName());
        employee.setAge(employeeDetails.getAge());
        employee.setEmployeeType(employeeDetails.getEmployeeType());
        return employeeRepository.save(employee);
    }
}
