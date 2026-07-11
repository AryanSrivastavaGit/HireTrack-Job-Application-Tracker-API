package com.aryan.hireTrack.dto;

import com.aryan.hireTrack.entity.Outcome;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoundDetailDto {
    private Long id;
    private String round;
    private String description;
    private Outcome outcome;
}
