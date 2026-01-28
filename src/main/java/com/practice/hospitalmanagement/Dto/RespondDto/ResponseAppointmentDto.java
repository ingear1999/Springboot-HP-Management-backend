package com.practice.hospitalmanagement.Dto.RespondDto;

import com.practice.hospitalmanagement.Entity.usersEntity.Doctor;
import com.practice.hospitalmanagement.status.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAppointmentDto {

    private String name;
    private String contactInfo;
    private LocalDate date;
    private AppointmentStatus status;

}
