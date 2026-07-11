package com.aryan.hireTrack.dto;

import com.aryan.hireTrack.entity.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HireTrackRequestDto {
    private String appliedPortal;

    @NotBlank(message = "Job role can not be empty")
    private String jobRole;

    @NotBlank(message = "Job type can not be empty")
    private String jobType;

    private String roleDescription;
    private Double minSalary;
    private Double maxSalary;
    private String currency;

    @NotNull(message = "Application status can not be null")
    private Status applicationStatus;

    @NotNull(message = "Company details are required")
    @Valid
    private CompanyRequestDto company;

    private List<CompanySourceRequestDto> companySources;
    private List<RoundDetailRequestDto> roundDetails;
    private List<ImportantDateRequestDto> importantDates;
    private List<NoteRequestDto> notes;
}