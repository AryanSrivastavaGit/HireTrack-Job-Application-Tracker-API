package com.aryan.hireTrack.controller;

import com.aryan.hireTrack.dto.HireTrackRequestDto;
import com.aryan.hireTrack.dto.HireTrackResponseDto;
import com.aryan.hireTrack.service.HireTrackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/hireTrack")
public class HireTrackController {

    @Autowired
    private HireTrackService hireTrackService;

    @GetMapping("/getAllHireTrackOfUser")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getHireTrackOfUser(@AuthenticationPrincipal UserDetails userDetails,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "7") int size,
                                                @RequestParam(required = false) String applicationStatus,
                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
                                                @RequestParam(required = false) String companyName,
                                                @RequestParam(required = false) String jobRole,
                                                @RequestParam(required = false) String jobType,
                                                @RequestParam(required = false) String companyCountry,
                                                @RequestParam(required = false) String companyState,
                                                @RequestParam(required = false) String companyCity,
                                                @RequestParam(required = false) Double minSalary,
                                                @RequestParam(required = false) Double maxSalary,
                                                @RequestParam(required = false) String currency) {
        return new ResponseEntity<>(hireTrackService.getHireTrackOfUser(userDetails.getUsername(), page, size,
                applicationStatus, dateFrom, dateTo, companyName, jobRole, jobType,
                companyCountry, companyState, companyCity, minSalary, maxSalary, currency), HttpStatus.OK);
    }

    @PostMapping("/addHireTrackOfUser")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> addHireTrackOfUser(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody HireTrackRequestDto hireTrackRequestDto){
        hireTrackService.addHireTrackOfUser(userDetails.getUsername(), hireTrackRequestDto);
        return new ResponseEntity<>("Hire track added successfully", HttpStatus.OK);
    }

    @PutMapping("/updateHireTrackOfUser")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateHireTrackOfUser(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody HireTrackResponseDto hireTrackResponseDto){
        hireTrackService.updateHireTrackOfUser(userDetails.getUsername(), hireTrackResponseDto);
        return new ResponseEntity<>("Hire track updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteHireTrackOfUser/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteHireTrackOfUser(@PathVariable Long id){
        hireTrackService.deleteHireTrackOfUser(id);
        return new ResponseEntity<>("Hire track deleted successfully", HttpStatus.OK);
    }
}