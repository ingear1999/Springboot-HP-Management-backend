package com.practice.hospitalmanagement.Dto.RespondDto;

import com.practice.hospitalmanagement.exception.DoctorNoFound;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDoctorException {
    LocalDateTime dateTime;
    String errorMessage;
    int value;
    String message;
}
