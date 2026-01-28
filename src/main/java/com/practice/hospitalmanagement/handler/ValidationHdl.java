package com.practice.hospitalmanagement.handler;

import com.practice.hospitalmanagement.Dto.RespondDto.RespondUsersExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

//Because validation errors are not business logic errors thar's why we use MethodArgumentNotValidException = input validation failure
@RestControllerAdvice
public class ValidationHdl {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespondUsersExceptionDto> validateUserRequest(MethodArgumentNotValidException manve){
        String message = manve.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");


        RespondUsersExceptionDto dto = new RespondUsersExceptionDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                message
        );


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }
}
