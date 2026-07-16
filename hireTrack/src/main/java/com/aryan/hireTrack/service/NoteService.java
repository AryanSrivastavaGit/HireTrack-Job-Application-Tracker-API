package com.aryan.hireTrack.service;

import com.aryan.hireTrack.dto.NoteDto;
import com.aryan.hireTrack.entity.Note;
import com.aryan.hireTrack.mapper.HireTrackMapper;
import com.aryan.hireTrack.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Transactional(readOnly = true)
    public List<NoteDto> getNotesOfUser(String username) {
        List<Note> notes = noteRepository.findAllNotesByUserEmail(username);
        return HireTrackMapper.toNoteDtoList(notes);
    }
}
