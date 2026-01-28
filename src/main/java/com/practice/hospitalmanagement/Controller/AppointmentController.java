package com.practice.hospitalmanagement.Controller;

import com.practice.hospitalmanagement.Dto.RequestDto.RequestAppointmentDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseAppointmentDto;
import com.practice.hospitalmanagement.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired // spring made the appointmentService for controller
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    }






