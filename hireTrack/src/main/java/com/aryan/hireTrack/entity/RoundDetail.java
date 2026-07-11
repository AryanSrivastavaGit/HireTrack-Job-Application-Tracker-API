package com.aryan.hireTrack.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoundDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String round;
    private String description;

    @Enumerated(EnumType.STRING)
    private Outcome outcome;

    @ManyToOne
    @JoinColumn(name = "hire_track_id")
    @JsonIgnoreProperties("roundDetails")
    private HireTrack hireTrack;
}
