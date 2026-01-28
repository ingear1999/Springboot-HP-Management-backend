package com.practice.hospitalmanagement.Repository;

import com.practice.hospitalmanagement.Entity.apointmentEntity.Appointment;
import com.practice.hospitalmanagement.status.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppoinmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByUsersIdAndStatus(Long usersId, AppointmentStatus status);
    Optional<Appointment> findByUsers_IdAndDoctor_Id(Long usersId, Long doctorId);
    List<Appointment> findByDoctor_IdAndStatus(long doctorId,AppointmentStatus status);
    Optional<Appointment> findByDoctor_IdAndIdAndStatus( long doctorId,long appointmentId,AppointmentStatus status);


    //Repository method names follow ENTITY FIELD NAMES, not column names ex Users users then findbyUsers not findByuser
    List<Appointment> findByUsers_Id(long usersId);

}
