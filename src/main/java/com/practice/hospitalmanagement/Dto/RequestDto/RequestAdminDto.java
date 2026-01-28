package com.practice.hospitalmanagement.Dto.RequestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
//@AllArgsConstructor already define down there (Here)
@NoArgsConstructor
@ToString
public class RequestAdminDto {
    // (int Id; // user input their id differnt from Entity id)
    @NotNull
    @Size(min=1,max=20)
    String username;
    @NotNull
    @Size(min=1,max=20)
    String password;
    @Email
    String email;
    public RequestAdminDto(String username, String password, String email) {  //(Here)
        this.username = username;
        this.password = password;
        this.email = email;

    }
}
