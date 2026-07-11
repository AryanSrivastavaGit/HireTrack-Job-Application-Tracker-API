package com.aryan.hireTrack.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanySourceDto {
    private Long id;
    private String source;
    private String link;
}