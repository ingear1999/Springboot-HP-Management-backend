package com.practice.hospitalmanagement.Entity.apointmentEntity;


import com.practice.hospitalmanagement.Entity.usersEntity.Doctor;
import com.practice.hospitalmanagement.Entity.usersEntity.Users;
import com.practice.hospitalmanagement.status.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name ="appointment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointmentPerson", nullable = false)
    private String name;

    @Column(name = "contactInfo", nullable = false)
    private String contactInfo;

    @Column(name = "appointmentDate", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="usersId")
    private Users users;

    @Enumerated(EnumType.STRING) //DB stores PENDING... instead of 0,1 ..
    private AppointmentStatus status;

    @CreationTimestamp //When this entity is FIRST saved, automatically set the current time.(to hibernate)
    @Column(updatable = false)
    private LocalDateTime createdAt;

}

//can not use @Data and FetchType.LAZY together because the lazy one select the request data but
// @Data has toString which is has all the data

// Do NOT use @Data with LAZY relationships.
// @Data generates toString(), equals(), and hashCode(),
// which may access lazy-loaded fields and trigger
// LazyInitializationException when the session is closed.

