package com.practice.hospitalmanagement.Service;

import com.practice.hospitalmanagement.Dto.RequestDto.RequestDoctorDto;
import com.practice.hospitalmanagement.Dto.RequestDto.UpdateDoctorInfo;
import com.practice.hospitalmanagement.Dto.RequestDto.UpdateUserPasswordDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseAppointmentDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseDoctorDto;
import com.practice.hospitalmanagement.Entity.apointmentEntity.Appointment;
import com.practice.hospitalmanagement.Entity.usersEntity.Doctor;
import com.practice.hospitalmanagement.Repository.AppointmentRepository;
import com.practice.hospitalmanagement.Repository.DoctorRepository;
import com.practice.hospitalmanagement.status.AppointmentStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepository doctorRepository,
                         AppointmentRepository appointmentRepository, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================================
    // Create doctor account
    // =========================================
    public ResponseDoctorDto saveDoctor(RequestDoctorDto dto) {

        Doctor doctor = new Doctor();
        doctor.setDepartment(dto.getDepartment());
        doctor.setUserName(dto.getUserName());
        doctor.setFirstName(dto.getFirstName());
        doctor.setLastName(dto.getLastName());
        doctor.setEmail(dto.getEmail());
        doctor.setPhoneNumber(dto.getPhoneNumber());
        doctor.setPassword(passwordEncoder.encode(dto.getPassword())); // later: encrypt

        Doctor savedDoctor = doctorRepository.save(doctor);

        ResponseDoctorDto response = new ResponseDoctorDto();
        response.setUserName(savedDoctor.getUserName());
        response.setDepartment(savedDoctor.getDepartment());

        return response;
    }

    // =========================================
    // Doctor views ONLY his pending appointments
    // =========================================
    public List<ResponseAppointmentDto> getPendingAppointments(long doctorId) {//which appointment belong to this doctor that's why needed doctorId

        return appointmentRepository
                .findByDoctor_IdAndStatus(doctorId,AppointmentStatus.PENDING)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // =========================================
    // Doctor updates appointment status
    // =========================================
    public ResponseAppointmentDto updateAppointmentStatus(
            long doctorId,long appointmentId,
            AppointmentStatus status) {

        // 1. Find appointment
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // 2. Check doctor exists
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // 3. Verify appointment belongs to this doctor
        if (!appointment.getDoctor().getId().equals(doctor.getId())) {
            throw new RuntimeException("Doctor not authorized to update this appointment");
        }

        // 4. Only PENDING appointments can be updated
        if (appointment.getStatus() != AppointmentStatus.PENDING) {
            throw new IllegalStateException("Only pending appointments can be updated");
        }

        // 5. Update status
        appointment.setStatus(status);
        appointment.setDoctor(doctor);

        // 6. Save and return response
        Appointment saved = appointmentRepository.save(appointment);
        return mapToResponse(saved);
    }

    // =========================================
    // Doctor views ACCEPTED appointments
    // =========================================
    public List<ResponseAppointmentDto> getAcceptedAppointments(long doctorId) {

        return appointmentRepository
                .findByDoctor_IdAndStatus(doctorId,AppointmentStatus.ACCEPTED)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // =========================================
    // Mapper method (Entity → DTO)
    // =========================================
    private ResponseAppointmentDto mapToResponse(Appointment appointment) {

        return new ResponseAppointmentDto(
                appointment.getName(),
                appointment.getUsers(),
                appointment.getContactInfo(),
                appointment.getDoctor(),
                appointment.getDate(),
                appointment.getStatus()
        );
    }


    //Update Doctor Infor Service
    //===========================================================

    public void updateDoctorInfo(long id, UpdateDoctorInfo dto){
        boolean update = false;
        Doctor  doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if(!doctor.getFirstName().equals(dto.getFirstName())){
            doctor.setFirstName(dto.getFirstName());
            update = true;
        }
        if(!doctor.getLastName().equals(dto.getLastName())){
            doctor.setLastName(dto.getLastName());
            update = true;
        }
        if(!doctor.getEmail().equals(dto.getEmail())){
            doctor.setEmail(dto.getEmail());
            update = true;
        }
        if (!doctor.getPhoneNumber().equals(dto.getContactNo())){
            doctor.setPhoneNumber(dto.getContactNo());
            update = true;
        }
        if(!update){
            throw new RuntimeException("Nothing to update");
        }
        doctorRepository.save(doctor);
    }


     //Password Upodate Service
    //============================================================

    public void updatePassword(long id, UpdateUserPasswordDto dto) {
       Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
//        if(passwordEncoder.matches(dto.getOldPassword(), doctor.getPassword())) {//need to input the old password to math the used password
//            doctor.setPassword(dto.getNewPassword());
//        }else{
//            throw new RuntimeException("Old password does not match");
//
//        doctorRepository.save(doctor); will save the raw password in to the DB

        if(!passwordEncoder.matches(dto.getOldPassword(), doctor.getPassword())){
            throw new RuntimeException("Old password does not match");
        }

        doctor.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        doctorRepository.save(doctor);
    }

    //Delete the acc Service
    //==============================================================

    public void deleteDoctor(long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctorRepository.delete(doctor);
    }
}
