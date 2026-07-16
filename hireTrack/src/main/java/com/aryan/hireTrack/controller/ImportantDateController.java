package com.aryan.hireTrack.controller;

import com.aryan.hireTrack.service.ImportantDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/importantDate")
public class ImportantDateController {

    @Autowired
    private ImportantDateService importantDateService;

    @GetMapping("/getImportantDatesOfUser")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getImportantDatesOfUser(@AuthenticationPrincipal UserDetails userDetails){
        return new ResponseEntity<>(importantDateService.getImportantDatesOfUser(userDetails.getUsername()), HttpStatus.OK);
    }
}
