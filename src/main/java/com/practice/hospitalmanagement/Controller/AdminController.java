package com.practice.hospitalmanagement.Controller;

import com.practice.hospitalmanagement.Dto.RequestDto.RequestAdminDto;
import com.practice.hospitalmanagement.Dto.RespondDto.RespondAdminDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseAppointmentDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseDoctorDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseUserDto;
import com.practice.hospitalmanagement.Entity.usersEntity.Users;
import com.practice.hospitalmanagement.Service.AdminService;
import com.practice.hospitalmanagement.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    // AdminController Contructor to call AdminService
    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    //==============================================================================
    //Overview User
    //==============================================================================
  @GetMapping("/All users")
    public List<ResponseUserDto> getAllUsers(){
        return adminService.getAllUsers();
  }

    //==============================================================================
    //Disable User
    //==============================================================================
  @PutMapping("/users/{userId}/Disable User")
    public void disableUser(@PathVariable("userId") long userId){
      adminService.disableUser(userId);
  }

    //==============================================================================
    //Overview Doctors
    //==============================================================================
  @GetMapping("/doctors")
    public List<ResponseDoctorDto> getAllDoctors(){
        return adminService.getAllDoctors();
  }

  @GetMapping("/appointment")
    public List<ResponseAppointmentDto> getAllAppointments(){
        return adminService.getAllAppointments();
  }


}
