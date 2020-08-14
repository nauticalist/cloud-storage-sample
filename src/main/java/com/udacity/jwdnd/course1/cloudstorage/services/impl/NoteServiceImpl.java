package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.dto.CredentialDto;
import com.udacity.jwdnd.course1.cloudstorage.controller.dto.NoteDto;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteMapper noteMapper;
    private final UserService userService;

    @Autowired
    public NoteServiceImpl(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }


    @Override
    public List<NoteDto> getAllNotes(String username) throws Exception{
        User user = userService.getUser(username);
        if (user == null)
            throw new Exception("Unable to find user:  " + username);
        List<Note> notes = noteMapper.findAllByUserId(user.getUserId());
        return notes.stream().map(this::mapNoteToNoteDto).collect(Collectors.toList());
    }

    @Override
    public NoteDto getNoteByNoteId(Integer noteId) throws Exception {
        Note note =  noteMapper.findByNoteId(noteId);
        if (note == null) {
            throw new Exception("Unable to find note with id:  " + noteId);
        }

        if (!this.verifyOwner(note.getUserId())) {
            throw new Exception("Unauthorized access to note detected: " + noteId);
        }

        return this.mapNoteToNoteDto(note);
    }

    @Override
    public int createNote(Note note, String username) throws Exception {
        User user = userService.getUser(username);
        if (user == null)
            throw new Exception("Unable to find user:  " + username);

        int newNoteId = noteMapper.create(new Note(null, note.getNoteTitle(), note.getNoteDescription(), user.getUserId()));
        if (newNoteId < 1)
            throw new Exception("Unable to save note");

        return newNoteId;
    }

    @Override
    public boolean deleteNote(Integer noteId) throws Exception{
        Note note = noteMapper.findByNoteId(noteId);

        if (note == null) {
            throw new Exception("Unable to note with id:  " + noteId);
        }

        if (!this.verifyOwner(note.getUserId())) {
            throw new Exception("Unauthorized access to note detected: " + noteId);
        }

        return noteMapper.delete(noteId);
    }

    @Override
    public int updateNote(Note note) throws Exception{
        if (noteMapper.findByNoteId(note.getNoteId()) == null) {
            throw new Exception("Unable to note with id:  " + note.getNoteId());
        }

        int updatedNote = noteMapper.update(note.getNoteId(), note.getNoteTitle(), note.getNoteDescription());

        if (updatedNote < 1)
            throw new Exception("Unable to save note");

        return updatedNote;
    }

    private NoteDto mapNoteToNoteDto(Note note) {
        return new NoteDto(note.getNoteId(), note.getNoteTitle(), note.getNoteDescription());
    }

    private boolean verifyOwner(int userId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        User user = userService.getUser(username);

        return (user.getUserId().equals(userId));
    }
}
