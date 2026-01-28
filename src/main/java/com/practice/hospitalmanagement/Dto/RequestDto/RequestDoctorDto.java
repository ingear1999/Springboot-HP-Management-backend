package com.practice.hospitalmanagement.Dto.RequestDto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDoctorDto {

    @NotBlank
    @Size(max = 50)
    private String department;

    @NotBlank
    @Size(max = 30)
    private String userName;

    @NotBlank
    @Size(max = 30)
    private String firstName;

    @NotBlank
    @Size(max = 30)
    private String lastName;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 20)
    private String phoneNumber;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
}
