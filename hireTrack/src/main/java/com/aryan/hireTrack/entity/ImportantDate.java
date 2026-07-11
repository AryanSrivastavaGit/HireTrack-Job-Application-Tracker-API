package com.aryan.hireTrack.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImportantDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate eventDate;
    private String eventTitle;

    private LocalDateTime notifyAt;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean notified = false;

    @ManyToOne
    @JoinColumn(name = "hire_track_id")
    @JsonIgnoreProperties("importantDates")
    private HireTrack hireTrack;
}