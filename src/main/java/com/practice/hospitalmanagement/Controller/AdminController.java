package com.practice.hospitalmanagement.Controller;


import com.practice.hospitalmanagement.Dto.RespondDto.ResponseAppointmentDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseDoctorDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseUserDto;
import com.practice.hospitalmanagement.Service.AdminService;
import com.practice.hospitalmanagement.Service.UserService;
import com.practice.hospitalmanagement.status.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/users/getAllUsers")
    public Page<ResponseUserDto> getAllUsers(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)//@PageableDefault is NOT a lock — it’s a backup
            Pageable pageable
    ) {
        return adminService.getAllUsers(pageable);
    }

    //==============================================================================
    //Find User By Id
    //==============================================================================

    @GetMapping("/users/{userId}")
    public ResponseUserDto getUserById(@PathVariable long userId) {
        return adminService.getUserById(userId);
    }



    //==============================================================================
    //Disable User
    //==============================================================================
  @PutMapping("/users/{userId}/DisableUser")
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


    //==============================================================================
    //FInd  Doctors By Id
    //==============================================================================

  @GetMapping("/doctors/{doctorId}")
  public ResponseDoctorDto getDoctorById(@PathVariable long doctorId){
        return adminService.getDoctorById(doctorId);
  }


    //==============================================================================
    //Find All the Appointment
    //==============================================================================
  @GetMapping("/appointments")
    public Page<ResponseAppointmentDto> getAllAppointments(Pageable pageable){
        return adminService.getAllAppointments(pageable);
  }


    //==============================================================================
    //Find Appointment from the User By Id
    //==============================================================================

    @GetMapping("/users/{userId}/appointment")
    public List<ResponseAppointmentDto> getAppointmentById(@PathVariable long userId) {
        return adminService.getAllAppointmentfromUser(userId);
    }


    //==============================================================================
    //Find Appointment from the Doctor By Id
    //==============================================================================
    @GetMapping("/doctors/{doctorId}/appointments")
    public List<ResponseAppointmentDto> getAppointmentFromDoctor(@PathVariable long doctorId) {
        return adminService.getAllAppointmentfromDoctor(doctorId);
    }

    //==============================================================================
    //Find Appointment from the Status
    //==============================================================================
    @GetMapping("/status/{status}/appointments")
    public Page<ResponseAppointmentDto> getAppointmentFromStatus(@PathVariable AppointmentStatus status,Pageable pageable) {
        return adminService.getAllAppointmentByStatus(status,pageable);
    }


}
