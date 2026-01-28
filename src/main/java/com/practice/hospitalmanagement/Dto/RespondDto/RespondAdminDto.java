package com.practice.hospitalmanagement.Dto.RespondDto;

import com.practice.hospitalmanagement.Entity.usersEntity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RespondAdminDto {
    int id;
    String username;
    String password;
}
