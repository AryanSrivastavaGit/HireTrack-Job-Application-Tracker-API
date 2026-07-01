package com.aryan.hireTrack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RequestRegister {
    @NotBlank(message = "Full name can not be empty")
    @Size(min = 3, message = "Min length should be 3 characters")
    private String fullName;

    @NotBlank(message = "Email can not be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Min length should be 8 characters")
    private String password;
}
