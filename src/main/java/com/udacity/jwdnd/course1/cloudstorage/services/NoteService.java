package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.controller.dto.NoteDto;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;

import java.util.List;

public interface NoteService {
    List<NoteDto> getAllNotes(String username) throws Exception;

    NoteDto getNoteByNoteId(Integer noteId) throws Exception;

    int createNote(Note note, String username) throws Exception;

    boolean deleteNote(Integer noteId) throws Exception;

    int updateNote(Note note) throws Exception;
}
