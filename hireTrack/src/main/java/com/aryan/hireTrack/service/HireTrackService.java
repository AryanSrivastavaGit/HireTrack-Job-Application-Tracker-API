package com.aryan.hireTrack.service;

import com.aryan.hireTrack.dto.HireTrackRequest;
import com.aryan.hireTrack.entity.HireTrack;
import com.aryan.hireTrack.entity.User;
import com.aryan.hireTrack.repository.HireTrackRepository;
import com.aryan.hireTrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HireTrackService {

    @Autowired
    private HireTrackRepository hireTrackRepository;

    @Autowired
    private UserRepository userRepository;

    public List<HireTrack> getAllHireTrack() {
        return hireTrackRepository.findAll();
    }

    public HireTrack addHireTrack(String username, HireTrackRequest hireTrackRequest) {

        User user = userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User does not exist"));

        HireTrack hireTrack = HireTrack.builder()
                .company(hireTrackRequest.getCompany())
                .appliedPortal(hireTrackRequest.getAppliedPortal())
                .jobRole(hireTrackRequest.getJobRole())
                .jobType(hireTrackRequest.getJobType())
                .roleDescription(hireTrackRequest.getRoleDescription())
                .minSalary(hireTrackRequest.getMinSalary())
                .maxSalary(hireTrackRequest.getMaxSalary())
                .currency(hireTrackRequest.getCurrency())
                .applicationStatus(hireTrackRequest.getApplicationStatus())
                .companySources(hireTrackRequest.getCompanySources())
                .roundDetails(hireTrackRequest.getRoundDetails())
                .importantDates(hireTrackRequest.getImportantDates())
                .notes(hireTrackRequest.getNotes())
                .user(user)
                .build();

        return hireTrackRepository.save(hireTrack);
    }

    public HireTrack updateHireTrack(HireTrack hireTrackRequest) {
        HireTrack hireTrack = HireTrack.builder()
                .id(hireTrackRequest.getId())
                .company(hireTrackRequest.getCompany())
                .appliedPortal(hireTrackRequest.getAppliedPortal())
                .jobRole(hireTrackRequest.getJobRole())
                .jobType(hireTrackRequest.getJobType())
                .roleDescription(hireTrackRequest.getRoleDescription())
                .minSalary(hireTrackRequest.getMinSalary())
                .maxSalary(hireTrackRequest.getMaxSalary())
                .currency(hireTrackRequest.getCurrency())
                .applicationStatus(hireTrackRequest.getApplicationStatus())
                .companySources(hireTrackRequest.getCompanySources())
                .roundDetails(hireTrackRequest.getRoundDetails())
                .importantDates(hireTrackRequest.getImportantDates())
                .notes(hireTrackRequest.getNotes())
                .user(hireTrackRequest.getUser())
                .build();
        return hireTrackRepository.save(hireTrack);
    }

    public void deleteHireTrack(Long id) {
        hireTrackRepository.deleteById(id);
    }

}
