package com.practice.hospitalmanagement.handler;


import com.practice.hospitalmanagement.Dto.RespondDto.ResponseDoctorException;
import com.practice.hospitalmanagement.exception.AppointmentCustomException;
import com.practice.hospitalmanagement.exception.DoctorNoFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class DoctorExceptionHdl {
    @ExceptionHandler(DoctorNoFound.class)
    public ResponseEntity<ResponseDoctorException> handleException(DoctorNoFound e) {
        ResponseDoctorException responseDoctorException = new ResponseDoctorException(
                LocalDateTime.now(),
                "Doctor Not Found",
                HttpStatus.BAD_REQUEST.value(), // get the value of bad request
                e.getMessage()

        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDoctorException); // HTTPStatus here is to show BAD_REQUEST message

    }

}
