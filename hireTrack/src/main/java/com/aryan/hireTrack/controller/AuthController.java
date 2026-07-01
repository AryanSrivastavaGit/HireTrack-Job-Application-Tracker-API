package com.aryan.hireTrack.controller;

import com.aryan.hireTrack.dto.LoginRequest;
import com.aryan.hireTrack.dto.RefreshTokenRequest;
import com.aryan.hireTrack.dto.RequestRegister;
import com.aryan.hireTrack.dto.TokenPair;
import com.aryan.hireTrack.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RequestRegister requestRegister){
        authService.registerUser(requestRegister);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        TokenPair tokenPair = authService.login(loginRequest);
        return new ResponseEntity<>(tokenPair, HttpStatus.OK);
    }

    @PostMapping("refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        TokenPair tokenPair = authService.refreshToken(refreshTokenRequest);
        return new ResponseEntity<>(tokenPair, HttpStatus.OK);
    }
}
