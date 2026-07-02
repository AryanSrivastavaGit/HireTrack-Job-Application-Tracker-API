package com.aryan.hireTrack.controller;

import com.aryan.hireTrack.dto.UserEmailRequest;
import com.aryan.hireTrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/userByEmail")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUser(@RequestBody UserEmailRequest userEmailRequest){
        return new ResponseEntity<>(userService.userById(userEmailRequest), HttpStatus.OK);
    }

    @DeleteMapping("deleteByEmail")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@RequestBody UserEmailRequest userEmailRequest){
        userService.deleteUser(userEmailRequest);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
