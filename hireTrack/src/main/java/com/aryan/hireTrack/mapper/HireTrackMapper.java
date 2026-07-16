package com.aryan.hireTrack.mapper;

import com.aryan.hireTrack.dto.*;
import com.aryan.hireTrack.entity.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HireTrackMapper {

//    response dto methods

    public static HireTrackResponseDto toHireTrackResponseDto(HireTrack hireTrack) {
        if (hireTrack == null) return null;

        return HireTrackResponseDto.builder()
                .id(hireTrack.getId())
                .appliedPortal(hireTrack.getAppliedPortal())
                .jobRole(hireTrack.getJobRole())
                .jobType(hireTrack.getJobType())
                .roleDescription(hireTrack.getRoleDescription())
                .minSalary(hireTrack.getMinSalary())
                .maxSalary(hireTrack.getMaxSalary())
                .currency(hireTrack.getCurrency())
                .createdAt(hireTrack.getCreatedAt())
                .updatedAt(hireTrack.getUpdatedAt())
                .applicationStatus(hireTrack.getApplicationStatus())
                .company(toCompanyDto(hireTrack.getCompany()))
                .companySources(toCompanySourceDtoList(hireTrack.getCompanySources()))
                .roundDetails(toRoundDetailDtoList(hireTrack.getRoundDetails()))
                .importantDates(toImportantDateDtoList(hireTrack.getImportantDates()))
                .notes(toNoteDtoList(hireTrack.getNotes()))
                .build();
    }

    public static List<HireTrackResponseDto> toHireTrackResponseDtoList(List<HireTrack> hireTracks) {
        if (hireTracks == null) return Collections.emptyList();
        return hireTracks.stream()
                .map(HireTrackMapper::toHireTrackResponseDto)
                .collect(Collectors.toList());
    }

    private static CompanyDto toCompanyDto(Company company) {
        if (company == null) return null;
        return CompanyDto.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .companyUrl(company.getCompanyUrl())
                .country(company.getCountry())
                .state(company.getState())
                .city(company.getCity())
                .district(company.getDistrict())
                .street(company.getStreet())
                .landmark(company.getLandmark())
                .building(company.getBuilding())
                .postalCode(company.getPostalCode())
                .build();
    }

    private static List<CompanySourceDto> toCompanySourceDtoList(List<CompanySource> sources) {
        if (sources == null) return Collections.emptyList();
        return sources.stream()
                .filter(Objects::nonNull)
                .map(s -> CompanySourceDto.builder()
                        .id(s.getId())
                        .source(s.getSource())
                        .link(s.getLink())
                        .build())
                .collect(Collectors.toList());
    }

    private static List<RoundDetailDto> toRoundDetailDtoList(List<RoundDetail> rounds) {
        if (rounds == null) return Collections.emptyList();
        return rounds.stream()
                .filter(Objects::nonNull)
                .map(r -> RoundDetailDto.builder()
                        .id(r.getId())
                        .round(r.getRound())
                        .description(r.getDescription())
                        .outcome(r.getOutcome())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<ImportantDateDto> toImportantDateDtoList(List<ImportantDate> dates) {
        if (dates == null) return Collections.emptyList();
        return dates.stream()
                .filter(Objects::nonNull)
                .map(d -> ImportantDateDto.builder()
                        .id(d.getId())
                        .eventDate(d.getEventDate())
                        .eventTitle(d.getEventTitle())
                        .notifyAt(d.getNotifyAt())
                        .notified(d.isNotified())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<NoteDto> toNoteDtoList(List<Note> notes) {
        if (notes == null) return Collections.emptyList();
        return notes.stream()
                .filter(Objects::nonNull)
                .map(n -> NoteDto.builder()
                        .id(n.getId())
                        .title(n.getTitle())
                        .content(n.getContent())
                        .build())
                .collect(Collectors.toList());
    }


//    request dto methods

    public static HireTrack toHireTrackEntity(HireTrackRequestDto dto, User user) {
        if (dto == null) return null;

        HireTrack hireTrack = HireTrack.builder()
                .appliedPortal(dto.getAppliedPortal())
                .jobRole(dto.getJobRole())
                .jobType(dto.getJobType())
                .roleDescription(dto.getRoleDescription())
                .minSalary(dto.getMinSalary())
                .maxSalary(dto.getMaxSalary())
                .currency(dto.getCurrency())
                .applicationStatus(dto.getApplicationStatus())
                .user(user)
                .build();

        hireTrack.setCompany(toCompanyEntity(dto.getCompany(), hireTrack));
        hireTrack.setCompanySources(toCompanySourceEntityList(dto.getCompanySources(), hireTrack));
        hireTrack.setRoundDetails(toRoundDetailEntityList(dto.getRoundDetails(), hireTrack));
        hireTrack.setImportantDates(toImportantDateEntityList(dto.getImportantDates(), hireTrack));
        hireTrack.setNotes(toNoteEntityList(dto.getNotes(), hireTrack));

        return hireTrack;
    }

    private static Company toCompanyEntity(CompanyRequestDto dto, HireTrack hireTrack) {
        if (dto == null) return null;
        return Company.builder()
                .companyName(dto.getCompanyName())
                .companyUrl(dto.getCompanyUrl())
                .country(dto.getCountry())
                .state(dto.getState())
                .city(dto.getCity())
                .district(dto.getDistrict())
                .street(dto.getStreet())
                .landmark(dto.getLandmark())
                .building(dto.getBuilding())
                .postalCode(dto.getPostalCode())
                .hireTrack(hireTrack)
                .build();
    }

    private static List<CompanySource> toCompanySourceEntityList(List<CompanySourceRequestDto> dtos, HireTrack hireTrack) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(dto -> CompanySource.builder()
                        .source(dto.getSource())
                        .link(dto.getLink())
                        .hireTrack(hireTrack).build())
                .collect(Collectors.toList());
    }

    private static List<RoundDetail> toRoundDetailEntityList(List<RoundDetailRequestDto> dtos, HireTrack hireTrack) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(dto -> RoundDetail.builder()
                        .round(dto.getRound())
                        .description(dto.getDescription())
                        .outcome(dto.getOutcome())
                        .hireTrack(hireTrack).build())
                .collect(Collectors.toList());
    }

    private static List<ImportantDate> toImportantDateEntityList(List<ImportantDateRequestDto> dtos, HireTrack hireTrack) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(dto -> ImportantDate.builder()
                        .eventDate(dto.getEventDate())
                        .eventTitle(dto.getEventTitle())
                        .notifyAt(dto.getNotifyAt())
                        .notified(false)
                        .hireTrack(hireTrack).build())
                .collect(Collectors.toList());
    }

    private static List<Note> toNoteEntityList(List<NoteRequestDto> dtos, HireTrack hireTrack) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(dto -> Note.builder()
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .hireTrack(hireTrack).build())
                .collect(Collectors.toList());
    }
}