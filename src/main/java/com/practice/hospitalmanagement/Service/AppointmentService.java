package com.practice.hospitalmanagement.Service;


import com.practice.hospitalmanagement.Dto.RequestDto.RequestAppointmentDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseAppointmentDto;
import com.practice.hospitalmanagement.Entity.apointmentEntity.Appointment;
import com.practice.hospitalmanagement.Entity.usersEntity.Doctor;
import com.practice.hospitalmanagement.Entity.usersEntity.Users;
import com.practice.hospitalmanagement.Repository.AppointmentRepository;
import com.practice.hospitalmanagement.Repository.DoctorRepository;
import com.practice.hospitalmanagement.Repository.UserRepository;
import com.practice.hospitalmanagement.exception.AppointmentCustomException;
import com.practice.hospitalmanagement.exception.DoctorNoFound;
import com.practice.hospitalmanagement.exception.UserNotFound;
import com.practice.hospitalmanagement.status.AppointmentStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
    }


    // Register a new appointment (default status: PENDING)
    // =====================================================
    public ResponseAppointmentDto registerAppointment(long usersId,long dcotorId,RequestAppointmentDto appointmentDto) {

        Users users = userRepository.findById(usersId).orElseThrow(()-> new UserNotFound(usersId));
        Doctor doctor = doctorRepository.findById(dcotorId).orElseThrow(()->new DoctorNoFound(dcotorId));

        boolean pendingAppointmentExited= appointmentRepository.existsByUsersIdAndStatus(usersId,AppointmentStatus.PENDING);

        if(pendingAppointmentExited){
            throw new AppointmentCustomException(usersId);
        }

        Appointment appointment = new Appointment();
        appointment.setName(appointmentDto.getName());
        appointment.setDate(appointmentDto.getDate());
        appointment.setContactInfo(appointmentDto.getContactInfo());
        appointment.setUsers(users); // setUser into the appointment to identify which user is booked the appointment
        appointment.setDoctor(doctor);// setDoctor into the appointment to identify which doctor was booked
        appointment.setStatus(AppointmentStatus.PENDING);

        Appointment saved = appointmentRepository.save(appointment);


        ResponseAppointmentDto  responseAppointmentDto = new ResponseAppointmentDto();
        responseAppointmentDto.setName(saved.getName());
        responseAppointmentDto.setContactInfo(saved.getContactInfo());
        responseAppointmentDto.setDate(saved.getDate());
        responseAppointmentDto.setStatus(saved.getStatus());


        return responseAppointmentDto;

    }


    // Update appointment status by doctor and  to the database after the doctor make decision
    // ========================================================================
//    public ResponseAppointmentDto updateAppointment(long doctorId,Long appointmentId, AppointmentStatus status) {
//        Appointment appointment = appointmentRepository
//                .findByDoctor_IdAndIdAndStatus(
//                        doctorId,
//                        appointmentId,
//                        AppointmentStatus.PENDING
//                )
//                .orElseThrow(() ->
//                        new RuntimeException("Pending appointment not found for this doctor"));
//
//        appointment.setStatus(status); // setStatus becasue the doctor is the one who decide and setStatus to update everytime of decision
//
//        Appointment saved = appointmentRepository.save(appointment);
//
//        return new ResponseAppointmentDto(
//                saved.getName(),
//                saved.getContactInfo(),
//                saved.getDate(),
//                saved.getStatus()
//        );
//
//    }


    //User  view the appointment that has been booked (Accepted or pendding)
    //=====================================================================================

    public List<ResponseAppointmentDto> getAppointmentsByUserId(Long usersId) {
        return appointmentRepository.findByUsers_Id(usersId)
                .stream()
                .map(appointment -> new ResponseAppointmentDto(
                        appointment.getName(),
                        appointment.getContactInfo(),
                        appointment.getDate(),
                        appointment.getStatus()
                ))
                .toList();
    }


    //User  Cancle The Appointment
    //=====================================================================================

    public void appointmentCancelation (long userId,long doctorId){
        Appointment appointment = appointmentRepository.findByUsers_IdAndDoctor_Id(userId,doctorId).orElseThrow(()->new UserNotFound(userId));
            appointmentRepository.delete(appointment);
    }

}



