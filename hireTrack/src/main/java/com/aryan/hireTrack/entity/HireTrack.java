package com.aryan.hireTrack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HireTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private CompanyAddress companyAddress;

    private String companyUrl;
    private String appliedPortal;
    private String jobRole;
    private String jobType;
    private String roleDescription;
    private String salaryOffering;

    @Enumerated(EnumType.STRING)
    private Status applicationStatus;

    @OneToMany(mappedBy = "hireTrack", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompanySource> companySources;

    @OneToMany(mappedBy = "hireTrack", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoundDetail> roundDetails;

    @OneToMany(mappedBy = "hireTrack", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImportantDate> importantDates;

    @OneToMany(mappedBy = "hireTrack", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    }