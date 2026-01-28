package com.practice.hospitalmanagement.Dto.RespondDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RespondUserDto {
    private String userName;
    private String fullName;
    private String email;
}
