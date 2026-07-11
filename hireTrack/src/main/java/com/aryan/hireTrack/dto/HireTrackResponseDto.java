package com.aryan.hireTrack.dto;

import com.aryan.hireTrack.entity.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HireTrackResponseDto {
    private Long id;
    private String appliedPortal;
    private String jobRole;
    private String jobType;
    private String roleDescription;
    private Double minSalary;
    private Double maxSalary;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status applicationStatus;

    private CompanyDto company;
    private List<CompanySourceDto> companySources;
    private List<RoundDetailDto> roundDetails;
    private List<ImportantDateDto> importantDates;
    private List<NoteDto> notes;
}