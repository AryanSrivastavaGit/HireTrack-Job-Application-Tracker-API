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
public class CompanySource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private String link;

    @ManyToOne
    @JoinColumn(name = "hire_track_id")
    @JsonIgnoreProperties("companySources")
    private HireTrack hireTrack;
}
