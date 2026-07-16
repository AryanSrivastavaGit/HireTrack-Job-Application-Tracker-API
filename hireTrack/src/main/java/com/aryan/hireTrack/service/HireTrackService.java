package com.aryan.hireTrack.service;

import com.aryan.hireTrack.dto.HireTrackRequestDto;
import com.aryan.hireTrack.dto.HireTrackResponseDto;
import com.aryan.hireTrack.dto.NoteDto;
import com.aryan.hireTrack.entity.HireTrack;
import com.aryan.hireTrack.entity.Note;
import com.aryan.hireTrack.entity.User;
import com.aryan.hireTrack.mapper.HireTrackMapper;
import com.aryan.hireTrack.repository.HireTrackRepository;
import com.aryan.hireTrack.repository.UserRepository;
import com.aryan.hireTrack.specification.HireTrackSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HireTrackService {

    @Autowired
    private HireTrackRepository hireTrackRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<HireTrackResponseDto> getHireTrackOfUser(String username,
                                                         int page,
                                                         int size,
                                                         String applicationStatus,
                                                         LocalDate dateFrom,
                                                         LocalDate dateTo,
                                                         String companyName,
                                                         String jobRole,
                                                         String jobType,
                                                         String companyCountry,
                                                         String companyState,
                                                         String companyCity,
                                                         Double minSalary,
                                                         Double maxSalary,
                                                         String currency) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not exist"));

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Specification<HireTrack> spec = HireTrackSpecification.withFilters(
                user.getId(), applicationStatus, dateFrom, dateTo, companyName,
                jobRole, jobType, companyCountry, companyState, companyCity,
                minSalary, maxSalary, currency
        );

        Page<HireTrack> hireTracks = hireTrackRepository.findAll(spec, pageable);
        return hireTracks.map(HireTrackMapper::toHireTrackResponseDto);
    }

    @Transactional
    public void addHireTrackOfUser(String username, HireTrackRequestDto hireTrackRequestDto) {
        User user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not exist"));
        HireTrack hireTrack = HireTrackMapper.toHireTrackEntity(hireTrackRequestDto, user);
        hireTrackRepository.save(hireTrack);
    }
}