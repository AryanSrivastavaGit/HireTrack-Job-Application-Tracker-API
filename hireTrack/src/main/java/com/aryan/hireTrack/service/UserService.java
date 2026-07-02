package com.aryan.hireTrack.service;

import com.aryan.hireTrack.dto.UserEmailRequest;
import com.aryan.hireTrack.entity.User;
import com.aryan.hireTrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User userById(UserEmailRequest userEmailRequest) {
        return userRepository.findByEmail(userEmailRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
    }

    public void deleteUser(UserEmailRequest userEmailRequest) {
        if(!userRepository.existsByEmail(userEmailRequest.getEmail())){
            throw new UsernameNotFoundException("User does not exist");
        }
        userRepository.deleteByEmail(userEmailRequest.getEmail());
    }
}