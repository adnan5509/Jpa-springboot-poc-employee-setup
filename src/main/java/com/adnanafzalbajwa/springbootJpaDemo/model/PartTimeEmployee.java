package com.adnanafzalbajwa.springbootJpaDemo.model;

import jakarta.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {
    private double hourlyRate;

    public PartTimeEmployee() {
    }

    public PartTimeEmployee(final String name, final int age, final double hourlyRate) {
        super(name, age);
        this.hourlyRate = hourlyRate;
    }



    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(final double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
