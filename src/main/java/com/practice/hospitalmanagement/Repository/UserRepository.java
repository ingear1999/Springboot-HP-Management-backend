package com.practice.hospitalmanagement.Repository;

import com.practice.hospitalmanagement.Entity.usersEntity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<Users,Long> {
//---------<User,Integer>---------------
//    ✅ It reads/writes User objects
//    ✅ It maps them to the users table

//    👉 Integer = type of the PRIMARY KEY (@Id)
    List<Users> findByLastActiveAtBefore(LocalDateTime date);
    Page<Users> findAll(Pageable pageable);

}
