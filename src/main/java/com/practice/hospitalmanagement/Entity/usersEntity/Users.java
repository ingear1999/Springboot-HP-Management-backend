package com.practice.hospitalmanagement.Entity.usersEntity;

import com.practice.hospitalmanagement.Entity.apointmentEntity.Appointment;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  long id;

    @Column(name="user_name")
    private String userName;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name="user_password")
    private String password;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    List <Appointment> appointment;

    @Column(nullable = false)//null able
    private boolean active = true;

    @CreationTimestamp //When this entity is FIRST saved, automatically set the current time.(to hibernate)
    @Column(updatable = false)
    private LocalDateTime createdAt;


    private LocalDateTime lastActiveAt;


}
