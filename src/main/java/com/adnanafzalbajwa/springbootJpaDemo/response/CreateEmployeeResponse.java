package com.adnanafzalbajwa.springbootJpaDemo.response;

import java.util.Date;

public class CreateEmployeeResponse {

    private int id;

    private String name;

    private int age;

    private double salary;

    private Date dateOfJoining;

    private String contactNo;

    private String email;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(final double salary) {
        this.salary = salary;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(final Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(final String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
