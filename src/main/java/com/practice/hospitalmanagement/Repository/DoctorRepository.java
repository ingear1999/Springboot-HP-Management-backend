package com.practice.hospitalmanagement.Repository;

import com.practice.hospitalmanagement.Entity.usersEntity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
