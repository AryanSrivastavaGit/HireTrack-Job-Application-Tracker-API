package com.aryan.hireTrack.controller;

import com.aryan.hireTrack.dto.HireTrackRequestDto;
import com.aryan.hireTrack.service.HireTrackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hireTrack")
public class HireTrackController {

    @Autowired
    private HireTrackService hireTrackService;

    @GetMapping("/getAllHireTrackOfUser")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getHireTrackOfUser(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "7") int size) {
        return new ResponseEntity<>(hireTrackService.getHireTrackOfUser(userDetails.getUsername(), page, size), HttpStatus.OK);
    }

    @PostMapping("/addHireTrackOfUser")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> addHireTrackOfUser(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody HireTrackRequestDto hireTrackRequestDto){
        hireTrackService.addHireTrackOfUser(userDetails.getUsername(), hireTrackRequestDto);
        return new ResponseEntity<>("Hire track added successfully", HttpStatus.OK);
    }
}