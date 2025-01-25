package com.adnanafzalbajwa.springbootJpaDemo.repository;

import com.adnanafzalbajwa.springbootJpaDemo.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void testFindByEmail_whenGivenCorrectEmail_returnsCorrectEmployee() {
        Employee employee = new Employee();
        employee.setName("Adnan");
        employee.setAge(25);
        employee.setEmail("test@test.com");
        employee.setContactNo("+35288888888");
        employee.setDateOfJoining(new Date());
        employee.setSalary(100000.00);

        Employee storedEmployee = testEntityManager.persistAndFlush(employee);
        Employee employeeFoundByEmail = employeeRepository.findByEmail(storedEmployee.getEmail());
        assertEquals(storedEmployee.getEmail(), employeeFoundByEmail.getEmail(), "The Email of the stored employee should match the found employee");
    }


    @Test
    void testFindEmployeesHavingGivenCountryCode_whenGivenCountryCode_returnsEmployeesList() {
        Employee employee1 = new Employee();
        employee1.setName("Adnan");
        employee1.setAge(30);
        employee1.setContactNo("+35212345678"); // LU Number
        employee1.setEmail("adnan@test.com");
        employee1.setSalary(50000.00);
        employee1.setDateOfJoining(new Date());

        Employee employee2 = new Employee();
        employee2.setName("Noor");
        employee2.setAge(28);
        employee2.setContactNo("+35287654321"); // LU Number
        employee2.setEmail("noor@test.com");
        employee2.setSalary(45000.00);
        employee2.setDateOfJoining(new Date());

        Employee employee3 = new Employee();
        employee3.setName("Ali");
        employee3.setAge(32);
        employee3.setContactNo("+35299887766"); // LU Number
        employee3.setEmail("ali@test.com");
        employee3.setSalary(60000.00);
        employee3.setDateOfJoining(new Date());

        Employee employee4 = new Employee();
        employee4.setName("John");
        employee4.setAge(35);
        employee4.setContactNo("+44123456789"); // UK Number
        employee4.setEmail("john@test.com");
        employee4.setSalary(70000.00);
        employee4.setDateOfJoining(new Date());

        Employee employee5 = new Employee();
        employee5.setName("Sarah");
        employee5.setAge(29);
        employee5.setContactNo("+91123456789"); // IN Number
        employee5.setEmail("sarah@test.com");
        employee5.setSalary(55000.00);
        employee5.setDateOfJoining(new Date());

        // Persist employees
        testEntityManager.persist(employee1);
        testEntityManager.persist(employee2);
        testEntityManager.persist(employee3);
        testEntityManager.persist(employee4);
        testEntityManager.persist(employee5);
        testEntityManager.flush();

        List<Employee> employeesHavingGivenCountryCode = employeeRepository.findEmployeesHavingGivenCountryCode("+352");

        assertEquals(3, employeesHavingGivenCountryCode.size(), "The number of employees having given country code number should be correct");
    }


}