package com.practice.hospitalmanagement.Entity.usersEntity;

import com.practice.hospitalmanagement.Entity.apointmentEntity.Appointment;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  long Id;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_password")
    private String password;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    List <Appointment> appointment;


}
