package com.aryan.hireTrack.service;


import com.aryan.hireTrack.dto.LoginRequest;
import com.aryan.hireTrack.dto.RefreshTokenRequest;
import com.aryan.hireTrack.dto.RequestRegister;
import com.aryan.hireTrack.dto.TokenPair;
import com.aryan.hireTrack.entity.Role;
import com.aryan.hireTrack.entity.User;
import com.aryan.hireTrack.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Transactional
    public TokenPair registerUser(RequestRegister requestRegister){
        if(userRepository.existsByEmail(requestRegister.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .fullName(requestRegister.getFullName())
                .email(requestRegister.getEmail())
                .password(passwordEncoder.encode(requestRegister.getPassword()))
                .role(Role.ROLE_ADMIN)
                .build();

        userRepository.save(user);

        LoginRequest loginRequest = LoginRequest.builder()
                .email(requestRegister.getEmail())
                .password(requestRegister.getPassword())
                .build();

        return login(loginRequest);
    }

    public TokenPair login(LoginRequest loginRequest){

        //authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // set authentication in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // generate TokenPair
        return jwtService.generateTokenPair(authentication);
    }

    public TokenPair refreshToken(RefreshTokenRequest refreshTokenRequest) {
        // check if it is a valid refresh token
        String refreshToken = refreshTokenRequest.getRefreshToken();

        if(!jwtService.isRefreshToken(refreshToken)){
            throw new IllegalArgumentException("Invalid refresh token");
        }

        String email = jwtService.extractEmail(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if(userDetails == null){
            throw new IllegalArgumentException("User not found");
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        String accessToken = jwtService.generateAccessToken(authentication);
        return new TokenPair(accessToken, refreshToken);
    }
}

