package com.practice.hospitalmanagement.handler;


import com.practice.hospitalmanagement.Dto.RespondDto.ResponseUsersExceptionDto;
import com.practice.hospitalmanagement.exception.AppointmentCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppointmentCustomExceptionHdl {
    @ExceptionHandler(AppointmentCustomException.class)
    public ResponseEntity<ResponseUsersExceptionDto> handleException(AppointmentCustomException apointmentCustomException) {
        ResponseUsersExceptionDto response=  new ResponseUsersExceptionDto(
                LocalDateTime.now(),
               HttpStatus.BAD_REQUEST.value(),
               "Exception Error ",
                apointmentCustomException.getMessage()

       );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
