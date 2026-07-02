package com.aryan.hireTrack.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyRequest {

    @NotBlank(message = "company name can not be empty")
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
