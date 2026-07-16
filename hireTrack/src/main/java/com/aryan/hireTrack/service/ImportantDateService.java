package com.aryan.hireTrack.service;

import com.aryan.hireTrack.dto.ImportantDateDto;
import com.aryan.hireTrack.entity.ImportantDate;
import com.aryan.hireTrack.mapper.HireTrackMapper;
import com.aryan.hireTrack.repository.ImportantDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImportantDateService {

    @Autowired
    private ImportantDateRepository importantDateRepository;

    @Transactional(readOnly = true)
    public List<ImportantDateDto> getImportantDatesOfUser(String username) {
        List<ImportantDate> importantDates = importantDateRepository.findAllImportantDatesByUserEmail(username);
        return HireTrackMapper.toImportantDateDtoList(importantDates);
    }
}