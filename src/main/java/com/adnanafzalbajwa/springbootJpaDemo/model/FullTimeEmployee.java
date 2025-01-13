package com.adnanafzalbajwa.springbootJpaDemo.model;

import jakarta.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee {

    private double salary;

    public FullTimeEmployee() {
    }

    public FullTimeEmployee(final String name, final int age, final double salary) {
        super(name, age);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(final double salary) {
        this.salary = salary;
    }
}
