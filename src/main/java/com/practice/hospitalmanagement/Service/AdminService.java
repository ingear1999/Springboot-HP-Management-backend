package com.practice.hospitalmanagement.Service;


import com.practice.hospitalmanagement.Dto.RequestDto.RequestAdminDto;
import com.practice.hospitalmanagement.Dto.RespondDto.RespondAdminDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseAppointmentDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseDoctorDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseUserDto;
import com.practice.hospitalmanagement.Entity.apointmentEntity.Appointment;
import com.practice.hospitalmanagement.Entity.usersEntity.Admin;
import com.practice.hospitalmanagement.Entity.usersEntity.Doctor;
import com.practice.hospitalmanagement.Entity.usersEntity.Users;
import com.practice.hospitalmanagement.Repository.AdminRepositoryImpl;

import com.practice.hospitalmanagement.Repository.AppointmentRepository;
import com.practice.hospitalmanagement.Repository.DoctorRepository;
import com.practice.hospitalmanagement.Repository.UserRepository;
import com.practice.hospitalmanagement.exception.UserNotFound;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepositoryImpl adminRepositoryImpl;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;


    public AdminService(AdminRepositoryImpl adminRepositoryImpl,
                        AppointmentRepository appointmentRepository,
                        DoctorRepository doctorRepository, UserRepository userRepository) {

        this.adminRepositoryImpl = adminRepositoryImpl;
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    public RespondAdminDto registerAdmin(RequestAdminDto admin){
        Admin adminEntity = new Admin();

        // Convert DTO TO ENTITY
        adminEntity.setUserName(admin.getUsername());
        adminEntity.setPassword(admin.getPassword());
        adminEntity.setEmails(admin.getEmail());

        //Save TO Respository
        Admin saved = adminRepositoryImpl.save(adminEntity); // saved from admin that declare in jap as Admin

          // Convert ENTITY TO DTO BY RespondAdmin
        return new RespondAdminDto(
                saved.getId(), // Admin save!!
                saved.getUserName(),
                saved.getEmails()
        );
    }


    //=========================================================================================================
    //OverView the Appointment
    //=========================================================================================================

    public List<ResponseAppointmentDto> getAllAppointments(){
        return appointmentRepository.findAll().stream().map(appointment-> new ResponseAppointmentDto(
                appointment.getName(),
                appointment.getUsers(),
                appointment.getContactInfo(),
                appointment.getDoctor(),
                appointment.getDate(),
                appointment.getStatus()
        )).toList();

    };

    //======================================================================================================
    //OverView the Doctor
    //======================================================================================================

    public List<ResponseDoctorDto> getAllDoctors(){
        return doctorRepository.findAll().stream().map(doctor -> new ResponseDoctorDto(
                doctor.getUserName(),
                doctor.getFirstName()+" "+doctor.getLastName(),
                doctor.getDepartment()
        )).toList();
    }


    //========================================================================================================
    //OverView the Users
    //========================================================================================================
    public List<ResponseUserDto> getAllUsers(){
        return userRepository.findAll().stream().map(users -> new ResponseUserDto(
                users.getUserName(),
                users.getFirstName()+" "+users.getLastName(),
                users.getEmail()
        )).toList();
    }

    public void disableUser(long id){
        Users users = userRepository.findById(id).orElseThrow(()->new UserNotFound(id));
        users.setActive(false);
        userRepository.save(users);
    }


}
