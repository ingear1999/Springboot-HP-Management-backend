package com.practice.hospitalmanagement.Controller;


import com.practice.hospitalmanagement.Dto.RequestDto.RequestDoctorDto;
import com.practice.hospitalmanagement.Dto.RequestDto.UpdateDoctorInfo;
import com.practice.hospitalmanagement.Dto.RequestDto.UpdateUserPasswordDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseAppointmentDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseDoctorDto;
import com.practice.hospitalmanagement.Service.DoctorService;
import com.practice.hospitalmanagement.status.AppointmentStatus;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    // Create a new doctor account by input in the dto then will save into the service
    // =====================================================
    @PostMapping("/doctorAcc")
    public ResponseDoctorDto createDoctorAccount(
            @Valid @RequestBody RequestDoctorDto requestDoctorDTO) {

        return doctorService.saveDoctor(requestDoctorDTO);
    }

    // Doctor views all PENDING appointments by showing the list
    // =====================================================
    @GetMapping("/{doctorId}/pendingAppointments")
    public List<ResponseAppointmentDto> getPendingAppointments( @PathVariable long doctorId) {

        return doctorService.getPendingAppointments(doctorId);
    }

    //{id} belong to the resorce not the action

    // Doctor updates appointment status
    // =====================================================
    @PutMapping("/{doctorId}/appointments/{appointmentId}/status")
    public ResponseAppointmentDto updateAppointmentStatus(
            @PathVariable long doctorId,
            @PathVariable long appointmentId,
            @RequestParam AppointmentStatus status) {

        return doctorService.updateAppointmentStatus( doctorId,appointmentId, status);
    }



    // Doctor accept/reject appointment status
    // =====================================================
    @GetMapping("/{doctorId}/appointments/accepted")
    public List<ResponseAppointmentDto> getAcceptedAppointments(@PathVariable long doctorId) {
        return doctorService.getAcceptedAppointments(doctorId);
    }

    //Update the Infor for Doctor
    //=======================================================
    @PutMapping("/doctor/{doctorId}/updateInfor)")
    public void updateDotorInfo(@PathVariable long doctorId,@Valid @RequestBody UpdateDoctorInfo  updateDoctorInfo) {
        doctorService.updateDoctorInfo(doctorId, updateDoctorInfo);
    }

    //Update the doctor Password
    //=======================================================
    @PutMapping("/{doctorId}/passwordUpdate")
    public void passWordUpdate(@PathVariable long doctorId, @Valid @RequestBody UpdateUserPasswordDto  updateUserPasswordDto) {
        doctorService.updatePassword(doctorId, updateUserPasswordDto);
    }

    //Delete the Doctor Acc
    //=======================================================
    @DeleteMapping("/{id}/delete")
    public void deleteDoctor(@PathVariable long id) {
        doctorService.deleteDoctor(id);
    }
}
