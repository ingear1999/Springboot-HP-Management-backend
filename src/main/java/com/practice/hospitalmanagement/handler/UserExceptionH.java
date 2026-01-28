

//Handlers exist to convert exceptions into HTTP responses, because services are not allowed to deal with HTTP logic.

package com.practice.hospitalmanagement.handler;


import com.practice.hospitalmanagement.Dto.RespondDto.ResponseUsersExceptionDto;
import com.practice.hospitalmanagement.exception.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
//= @ControllerAdvice + @ResponseBody
//Designed for REST APIs
//Always returns JSON
//No need to add @ResponseBody


//@RestControllerAdvice is used in REST APIs because it automatically returns JSON.
//@ControllerAdvice is more general and may return views unless @ResponseBody or ResponseEntity is used.”

public class UserExceptionH {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ResponseUsersExceptionDto> handleUserNotFound(UserNotFound userNotFound) {
//        ResponseEntity is just a wrapper box that contains:
//        1️⃣ HTTP status
//        2️⃣ HTTP headers
//        3️⃣ Response body<T> : T  = <RespondErrorExceptionDto>

        ResponseUsersExceptionDto errorDto = new ResponseUsersExceptionDto( //asigne the  raw value inside here
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "User Not Found",
                userNotFound.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDto);


    }
}
