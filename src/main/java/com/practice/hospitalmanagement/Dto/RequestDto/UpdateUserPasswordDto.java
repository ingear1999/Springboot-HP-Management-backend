package com.practice.hospitalmanagement.Dto.RequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserPasswordDto {

    @NotBlank
    @Size(max=20)
    private String oldPassword;

    @NotBlank
    @Size(max=20)
    private String newPassword;
}
