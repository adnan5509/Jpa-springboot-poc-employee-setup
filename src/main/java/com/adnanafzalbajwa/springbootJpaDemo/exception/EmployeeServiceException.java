package com.adnanafzalbajwa.springbootJpaDemo.exception;

public class EmployeeServiceException extends RuntimeException {

    public EmployeeServiceException(final String message) {
        super(message);
    }
}
