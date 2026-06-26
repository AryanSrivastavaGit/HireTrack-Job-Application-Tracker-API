package com.aryan.hireTrack.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HireTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "hireTrack", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("hireTrack")
    private Company company;

    private String appliedPortal;

    @Column(nullable = false)
    private String jobRole;

    @Column(nullable = false)
    private String jobType;

    private String roleDescription;
    private Double minSalary;
    private Double maxSalary;
    private String currency;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status applicationStatus;

    @OneToMany(mappedBy = "hireTrack", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("hireTrack")
    private List<CompanySource> companySources;

    @OneToMany(mappedBy = "hireTrack", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("hireTrack")
    private List<RoundDetail> roundDetails;

    @OneToMany(mappedBy = "hireTrack", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("hireTrack")
    private List<ImportantDate> importantDates;

    @OneToMany(mappedBy = "hireTrack", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("hireTrack")
    private List<Note> notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("hireTracks")
    private User user;
    }