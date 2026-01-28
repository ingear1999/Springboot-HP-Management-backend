package com.practice.hospitalmanagement.Dto.RespondDto;


import com.practice.hospitalmanagement.Entity.apointmentEntity.Appointment;
import com.practice.hospitalmanagement.Entity.usersEntity.Doctor;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDoctorDto {
    private String userName;
    private String fullName;
    private String department;
}
