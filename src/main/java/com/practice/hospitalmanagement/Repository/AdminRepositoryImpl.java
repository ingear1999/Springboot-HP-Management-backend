package com.practice.hospitalmanagement.Repository;

import com.practice.hospitalmanagement.Entity.apointmentEntity.Appointment;
import com.practice.hospitalmanagement.Entity.usersEntity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepositoryImpl extends JpaRepository<Admin,Integer> {

}
