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
import com.practice.hospitalmanagement.exception.DoctorNoFound;
import com.practice.hospitalmanagement.exception.UserNotFound;
import com.practice.hospitalmanagement.status.AppointmentStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ResponseAppointmentDto> getAllAppointments(Pageable pageable){
        Pageable safePageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );
        return appointmentRepository
                .findAll(safePageable)
                .map(appointment -> new ResponseAppointmentDto(
                        appointment.getName(),
                        appointment.getContactInfo(),
                        appointment.getDate(),
                        appointment.getStatus()
                ));

    }



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


    //======================================================================================================
    //Find Appointment from this  Doctor By Id
    //======================================================================================================
    public List<ResponseAppointmentDto> getAllAppointmentfromDoctor(long doctorId){

       Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new DoctorNoFound(doctorId
        )); // check the doctor fisrt before returning if there's no doctor then throw exception

        return appointmentRepository.findAllByDoctor_Id(doctorId).stream().map(appointment -> new ResponseAppointmentDto(
                appointment.getName(),
                appointment.getContactInfo(),
                appointment.getDate(),
                appointment.getStatus()
        )).toList(); // if there's no appointment from the doctor then return eampty list
    }


    //======================================================================================================
    //Find Appointment from this User By Id
    //======================================================================================================
    public List<ResponseAppointmentDto> getAllAppointmentfromUser(long userId){

       Users users = userRepository.findById(userId).orElseThrow(()-> new UserNotFound(userId
        )); // check the user fisrt before returning if there's no user then throw exception

        return appointmentRepository.findByUsers_Id(userId).stream().map(appointment -> new ResponseAppointmentDto(
                appointment.getName(),
                appointment.getContactInfo(),
                appointment.getDate(),
                appointment.getStatus()
        )).toList(); // if there's no appointment from the user then return eampty list
    }


    //======================================================================================================
    //Find Appointment from Status
    //======================================================================================================
//    public List<ResponseAppointmentDto> getAllAppointmentByStatus(AppointmentStatus status){
//
//        return appointmentRepository.findByStatus(status).stream().map(appointment -> new ResponseAppointmentDto(
//                appointment.getName(),
//                appointment.getContactInfo(),
//                appointment.getDate(),
//                appointment.getStatus()
//        )).toList(); // if there's no appointment from the user then return eampty list
//    }
    public Page<ResponseAppointmentDto> getAllAppointmentByStatus(
            AppointmentStatus status,
            Pageable pageable
    ) {

        Pageable safePageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        return appointmentRepository
                .findByStatus(status,safePageable)
                .map(appointment -> new ResponseAppointmentDto(
                        appointment.getName(),
                        appointment.getContactInfo(),
                        appointment.getDate(),
                        appointment.getStatus()
                ));
    }



    //======================================================================================================
    //Find Doctor By Id
    //======================================================================================================

    public ResponseDoctorDto getDoctorById(long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new DoctorNoFound(doctorId));
        return new ResponseDoctorDto(
                doctor.getUserName(),
                doctor.getFirstName() + " " + doctor.getLastName(),
                doctor.getEmail()
        );
    }


    //========================================================================================================
    //OverView the Users
    //========================================================================================================
    public Page<ResponseUserDto> getAllUsers(Pageable pageable) {
        Pageable safePageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );
        return userRepository.findAll(safePageable)
                .map(user -> new ResponseUserDto(
                        user.getUserName(),
                        user.getFirstName() + " " + user.getLastName(),
                        user.getEmail()
                ));
    }

    //======================================================================================================
    //Find Users By Id
    //======================================================================================================
    public ResponseUserDto getUserById(long id){
        Users user = userRepository.findById(id).orElseThrow(()-> new UserNotFound(id));
        return new ResponseUserDto(
                user.getUserName(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail()
        );
    }


    public void disableUser(long id){
        Users users = userRepository.findById(id).orElseThrow(()->new UserNotFound(id));
        users.setActive(false);
        userRepository.save(users);
    }


}
