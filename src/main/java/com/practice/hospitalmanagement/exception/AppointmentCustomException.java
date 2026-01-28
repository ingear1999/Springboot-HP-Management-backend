package com.practice.hospitalmanagement.exception;

public class AppointmentCustomException extends RuntimeException{
    public AppointmentCustomException(long id) {
        super("This User with this id "+id+" Appointment has been pending");
    }
}
