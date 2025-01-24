package com.adnanafzalbajwa.springbootJpaDemo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class CreateEmployeeRequest {

    @Size(min = 2, message = "Name must not be less than 2 characters")
    private String name;

    @Min(value = 0, message = "Age must not be less than 0")
    @Max(value = 150, message = "Age must not be greater than 150")
    private int age;

    @Min(value = 0, message = "Age must not be less than 0")
    private double salary;

    @JsonFormat(pattern = "EEE MMM dd HH:mm:ss zzz yyyy", timezone = "CET")
    private Date dateOfJoining;

    @Size(min = 7, max = 14, message = "Contact Number must be 7-14 characters")
    private String contactNo;

    @Email
    private String email;


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
