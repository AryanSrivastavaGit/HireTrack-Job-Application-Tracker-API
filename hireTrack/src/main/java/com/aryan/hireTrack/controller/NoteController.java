package com.aryan.hireTrack.controller;

import com.aryan.hireTrack.service.NoteService;
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
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/getNotesOfUser")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getNotesOfUser(@AuthenticationPrincipal UserDetails userDetails){
        return new ResponseEntity<>(noteService.getNotesOfUser(userDetails.getUsername()), HttpStatus.OK);
    }
}
