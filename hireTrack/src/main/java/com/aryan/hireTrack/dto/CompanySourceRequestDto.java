package com.aryan.hireTrack.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanySourceRequestDto {
    private String source;
    private String link;
}
