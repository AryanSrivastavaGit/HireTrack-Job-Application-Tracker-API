package com.aryan.hireTrack.controller;

import com.aryan.hireTrack.entity.User;
import com.aryan.hireTrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("getAll")
    public List<User> getUsers(){
        return userService.getUsers();
    }
}
