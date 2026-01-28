package com.practice.hospitalmanagement.Dto.RequestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserProfileDto {

    @NotBlank
    @Size(max=20)
    private String firstName;

    @NotBlank
    @Size(max=20)
    private String lastName;

    @NotBlank
    @Email
    @Size(max=20)
    private String email;

}
