package com.aryan.hireTrack.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImportantDateDto {
    private Long id;
    private LocalDate eventDate;
    private String eventTitle;
    private LocalDateTime notifyAt;
    private boolean notified;
}