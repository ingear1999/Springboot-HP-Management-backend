package com.practice.hospitalmanagement.Repository;

import com.practice.hospitalmanagement.Entity.apointmentEntity.Appointment;
import com.practice.hospitalmanagement.Entity.usersEntity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
//---------<User,Integer>---------------
//    ✅ It reads/writes User objects
//    ✅ It maps them to the users table

//    👉 Integer = type of the PRIMARY KEY (@Id)

    Appointment appointment = new Appointment();
}
