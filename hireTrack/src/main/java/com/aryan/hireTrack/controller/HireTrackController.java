package com.aryan.hireTrack.controller;

import com.aryan.hireTrack.dto.HireTrackRequest;
import com.aryan.hireTrack.entity.HireTrack;
import com.aryan.hireTrack.service.HireTrackService;
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

    @GetMapping("/getAll")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAllHireTrack(){
        return new ResponseEntity<>(hireTrackService.getAllHireTrack(), HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> addHireTrack(@AuthenticationPrincipal UserDetails user, @RequestBody HireTrackRequest hireTrackRequest){
        return new ResponseEntity<>(hireTrackService.addHireTrack(user.getUsername(), hireTrackRequest), HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateHireTrack(@RequestBody HireTrack hireTrack){
        return new ResponseEntity<>(hireTrackService.updateHireTrack(hireTrack),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteHireTrack(@PathVariable Long id){
        hireTrackService.deleteHireTrack(id);
        return new ResponseEntity<>("Hire Track successfully removed", HttpStatus.OK);
    }

}
