package com.aryan.hireTrack.repository;

import com.aryan.hireTrack.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n WHERE n.hireTrack.user.email = :email")
    List<Note> findAllNotesByUserEmail(@Param("email") String email);
}
