package com.practice.hospitalmanagement.Dto.RequestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestUserDTO {
    @NotNull
    @Size(min=1,max=20)
    private String firstName;

    @NotNull
    @Size(min=1,max=20)
    private String lastName;

    @NotNull
    @Size(min=1,max=20)
    private String userName;

    @NotNull(message = "must be a well-formed email address")
    @Email
    private String email;

    @NotNull
    @Size(min=1,max=20)
    private String password;

}
