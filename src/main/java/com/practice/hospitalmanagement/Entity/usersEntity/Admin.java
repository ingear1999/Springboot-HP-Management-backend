package com.practice.hospitalmanagement.Entity.usersEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@NoArgsConstructor
@Entity
@Table(name="Admin")

public class Admin {
//    @OneToMany(mappedBy = "doctor")
//    private List<Doctor> doctors = new ArrayList<>();

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  int Id; // from the database record not the user

    @Column(name="user_names")
    private String userName;

    @Column(name="user_passwords")
    private String password;

    @Column(name = "emails")
    private String emails;
}
