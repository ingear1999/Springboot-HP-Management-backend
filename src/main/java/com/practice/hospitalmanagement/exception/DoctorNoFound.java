package com.practice.hospitalmanagement.exception;

public class DoctorNoFound extends RuntimeException {
    public DoctorNoFound(long doctorId) {
        super("Doctor with this Id "+doctorId+" not found");
    }
}
