package com.practice.hospitalmanagement.Controller;

import com.practice.hospitalmanagement.Dto.RequestDto.RequestAppointmentDto;
import com.practice.hospitalmanagement.Dto.RequestDto.RequestUserDTO;
import com.practice.hospitalmanagement.Dto.RequestDto.UpdateUserPasswordDto;
import com.practice.hospitalmanagement.Dto.RequestDto.UpdateUserProfileDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseUserDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseAppointmentDto;
import com.practice.hospitalmanagement.Service.AppointmentService;
import com.practice.hospitalmanagement.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//no repository in controller
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;     // to talk to Service
    private final AppointmentService appointmentService;

    public UserController(UserService userService, AppointmentService appointmentService) {
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

     //Users Registeration
    //===============================================================================
    @PostMapping("/users")
    public ResponseUserDto getUserRequestDTO(
            @Valid @RequestBody RequestUserDTO userRequestDTO)// @RequestBody Converts the HTTP request body (JSON) → Java object
    {
        return  userService.registerUser(userRequestDTO); // it send and respond thorugh controller (the uservice class)
    }


    //Finding User
    //==============================================================================
//    @GetMapping("/users/{id}")
//    public ResponseUserDto getUserRespond(
//            @PathVariable int id)
//    {
//
//        return userService.findbyId(id);
//    }


    //Users Make Appointment
    //===========================================================================
    @PostMapping("/users/{userId}/appointments")
    public ResponseAppointmentDto book(
            @PathVariable Long userId,long doctorId,
            @Valid @RequestBody RequestAppointmentDto dto)
    {
        return appointmentService.registerAppointment( userId, doctorId,dto);

    }

    //User Check their Appointment(Status)
    //===========================================================================
    @GetMapping("/users/{userId}/appointments")
    public List<ResponseAppointmentDto> getAppointments(@PathVariable long userId) {
        return appointmentService.getAppointmentsByUserId(userId);
    }


    //Users Update Their Infor
    //===========================================================================
    @PutMapping("/users/{userId}")
    public ResponseUserDto updateUserInfo(@PathVariable long userId, @Valid @RequestBody UpdateUserProfileDto updateUserProfileDto){
        return userService.updateUserProfile(userId, updateUserProfileDto); // return RespondUserDto with these agruments (userId, updateUserProfileDto)
    }

    //Users Delete Their Acc
    //==========================================================================
    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long userId){
        userService.deleteAccount(userId);
    }

    //Users Update Their Password
    //=========================================================================
    @PutMapping("/users/{userId}/updatePassword")
    public void updatePassword(@PathVariable("userId") long id, @Valid @RequestBody UpdateUserPasswordDto dto){
        userService.updatePassword( id,dto);
    }

    //Users Cancel or Delete Their Appointment
    //============================================================================
    @DeleteMapping("/users/{userId}/doctor/{doctorId}/appointment")
    public void appointmentCancelation(@PathVariable long userId,@PathVariable long doctorId){
        appointmentService.appointmentCancelation(userId,doctorId);
    }

}
