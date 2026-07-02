package com.aryan.hireTrack.dto;

import com.aryan.hireTrack.entity.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HireTrackRequest {

    @NotNull(message = "company address can not be null")
    private Company company;

    private String appliedPortal;

    @NotBlank(message = "job role can not be empty")
    private String jobRole;

    @NotBlank(message = "job type can not be empty")
    private String jobType;

    @NotBlank(message = "role description can not be empty")
    private String roleDescription;

    private Double minSalary;
    private Double maxSalary;
    private String currency;

    @NotNull(message = "application status can not be null")
    private Status applicationStatus;

    private List<CompanySource> companySources;

    private List<RoundDetail> roundDetails;

    private List<ImportantDate> importantDates;

    private List<Note> notes;
}
