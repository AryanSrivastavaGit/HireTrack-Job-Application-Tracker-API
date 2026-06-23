package com.aryan.hireTrack.repository;

import com.aryan.hireTrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
