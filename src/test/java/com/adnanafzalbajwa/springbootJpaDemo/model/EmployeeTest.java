package com.adnanafzalbajwa.springbootJpaDemo.model;

import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
class EmployeeTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void testEmployeeEntity_whenValidUserDetailsProvided_returnsStoredEmployee() {
        Employee employee = new Employee();
        employee.setName("Adnan");
        employee.setAge(25);
        employee.setEmail("test@test.com");
        employee.setContactNo("+35288888888");
        employee.setDateOfJoining(new Date());
        employee.setSalary(100000.00);

        Employee storedEmployee = testEntityManager.persistAndFlush(employee);

        assertNotNull(storedEmployee.getId());
        assertEquals(employee.getName(), storedEmployee.getName());
        assertEquals(employee.getAge(), storedEmployee.getAge());
        assertEquals(employee.getEmail(), storedEmployee.getEmail());
        assertEquals(employee.getDateOfJoining(), storedEmployee.getDateOfJoining());
        assertEquals(employee.getSalary(), storedEmployee.getSalary());

    }

    @Test
    void testEmployeeEntity_whenIdIsNotUnique_ThrowsException() {
        Employee employee1 = new Employee();
        employee1.setName("Adnan");
        employee1.setAge(25);
        employee1.setEmail("test1@test.com");
        employee1.setContactNo("+35288888888");
        employee1.setDateOfJoining(new Date());
        employee1.setSalary(100000.00);

        Employee employee2 = new Employee();
        employee2.setName("Adnan");
        employee2.setAge(25);
        employee2.setEmail("test2@test.com");
        employee2.setContactNo("+35299999999");
        employee2.setDateOfJoining(new Date());
        employee2.setSalary(100000.00);

        // Persist the first employee
        testEntityManager.persistAndFlush(employee1);

        // Manually set the same ID for the second employee (which violates uniqueness)
        employee2.setId(employee1.getId());

        // Expect a PersistenceException or ConstraintViolationException
        assertThrows(PersistenceException.class, () -> testEntityManager.persistAndFlush(employee2));
    }


    @Test
    void testEmployeeEntity_whenNameIsNotValid_ThrowsException() {
        Employee employee = new Employee();
        employee.setName("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        employee.setAge(25);
        employee.setEmail("test@test.com");
        employee.setContactNo("+35288888888");
        employee.setDateOfJoining(new Date());
        employee.setSalary(100000.00);

        assertThrows(PersistenceException.class,()-> testEntityManager.persistAndFlush(employee));

    }

}