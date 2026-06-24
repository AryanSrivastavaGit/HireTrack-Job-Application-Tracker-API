package com.aryan.hireTrack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private HireTrack hireTrack;
}
