package com.aryan.hireTrack.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String email;
    private LocalDate dob;
    @OneToOne(cascade = CascadeType.ALL)
    private Address userAddress;
    private String username;
    private String password;
}