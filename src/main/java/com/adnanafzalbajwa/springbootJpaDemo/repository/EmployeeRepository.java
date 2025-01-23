package com.adnanafzalbajwa.springbootJpaDemo.repository;

import com.adnanafzalbajwa.springbootJpaDemo.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    Employee findByEmail(String email);
}
