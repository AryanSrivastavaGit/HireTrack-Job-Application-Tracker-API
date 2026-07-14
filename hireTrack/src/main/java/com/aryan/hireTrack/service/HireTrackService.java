package com.aryan.hireTrack.service;

import com.aryan.hireTrack.dto.HireTrackRequestDto;
import com.aryan.hireTrack.dto.HireTrackResponseDto;
import com.aryan.hireTrack.entity.HireTrack;
import com.aryan.hireTrack.entity.User;
import com.aryan.hireTrack.mapper.HireTrackMapper;
import com.aryan.hireTrack.repository.HireTrackRepository;
import com.aryan.hireTrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HireTrackService {

    @Autowired
    private HireTrackRepository hireTrackRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<HireTrackResponseDto> getHireTrackOfUser(String username, int page, int size) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not exist"));

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<HireTrack> hireTracks = hireTrackRepository.findAllByUser_Id(user.getId(), pageable);

        return hireTracks.map(HireTrackMapper::toHireTrackResponseDto);
    }

    @Transactional
    public void addHireTrackOfUser(String username, HireTrackRequestDto hireTrackRequestDto) {
        User user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not exist"));
        HireTrack hireTrack = HireTrackMapper.toHireTrackEntity(hireTrackRequestDto, user);
        hireTrackRepository.save(hireTrack);
    }
}