package com.practice.hospitalmanagement.Dto.RequestDto;


import com.practice.hospitalmanagement.Entity.usersEntity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAppointmentDto {
    private String name;
    private String contactInfo;
    private LocalDate date;
}
