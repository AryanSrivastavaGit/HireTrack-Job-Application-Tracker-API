package com.aryan.hireTrack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    private long id;
    private String country;
    private String state;
    private String city;
    private String district;
    private String street;
    private String landmark;
    private String building;
    private String postalCode;
}