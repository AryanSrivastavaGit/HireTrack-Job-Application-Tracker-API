package com.aryan.hireTrack.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {
    private Long id;
    private String companyName;
    private String companyUrl;
    private String country;
    private String state;
    private String city;
    private String district;
    private String street;
    private String landmark;
    private String building;
    private String postalCode;
}
