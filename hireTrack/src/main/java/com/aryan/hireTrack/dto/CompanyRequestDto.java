package com.aryan.hireTrack.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyRequestDto {
    @NotBlank(message = "Company name is required")
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
