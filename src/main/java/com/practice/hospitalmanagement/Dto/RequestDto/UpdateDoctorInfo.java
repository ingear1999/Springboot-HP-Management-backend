package com.practice.hospitalmanagement.Dto.RequestDto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDoctorInfo {
    @NotBlank
    @Size(max=20)
    String firstName;

    @NotBlank
    @Size(max=20)
    String lastName;

    @NotBlank
    @Email
    @Size(max=100)
    String email;

    @NotBlank
    @Size(max=20)
    String contactNo;
}
