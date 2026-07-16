package com.aryan.hireTrack.repository;

import com.aryan.hireTrack.entity.ImportantDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImportantDateRepository extends JpaRepository<ImportantDate, Long> {
    @Query("SELECT d FROM ImportantDate d WHERE d.hireTrack.user.email = :email")
    List<ImportantDate> findAllImportantDatesByUserEmail(@Param("email") String email);
}
