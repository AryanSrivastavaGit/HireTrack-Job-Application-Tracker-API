package com.aryan.hireTrack.repository;

import com.aryan.hireTrack.entity.HireTrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HireTrackRepository extends JpaRepository<HireTrack, Long> {
    List<HireTrack> findAllByUser_Id(Long id);
}
