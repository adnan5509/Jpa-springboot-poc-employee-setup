package com.adnanafzalbajwa.springbootJpaDemo.repository;

import com.adnanafzalbajwa.springbootJpaDemo.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    Employee findByEmail(String email);

    @Query("select emp FROM Employee emp WHERE emp.contactNo LIKE :countryCode%")
    List<Employee> findEmployeesHavingGivenCountryCode(@Param("countryCode") String countryCode);
}
