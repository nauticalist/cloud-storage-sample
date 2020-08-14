package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(value = "/notes")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping()
    public String saveNote(@ModelAttribute(name = "note") Note note, Model model, Principal principal){
        String errorMessage = null;

        if (note.getNoteId() == null) {
            try {
                noteService.createNote(note, principal.getName());
            } catch (Exception e) {
                errorMessage = e.getMessage();
            }
        } else {
            try {
                noteService.updateNote(note);
            } catch (Exception e) {
                errorMessage = e.getMessage();
            }
        }


        if (errorMessage != null)
            model.addAttribute("errorMessage", errorMessage);
        else
            model.addAttribute("successMessage", "Your note were successfully saved. Click <a id=\"success-link\" href=\"/#nav-notes\">here</a> to continue.");
        return "result";

    }

    @GetMapping(value = "/delete/{noteId}")
    public String deleteNote(@PathVariable("noteId") Integer noteId, Model model) {
        String errorMessage = null;

        try {
            noteService.deleteNote(noteId);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        if(errorMessage == null)
            model.addAttribute("successMessage", "Your note were successfully deleted. Click <a id=\"success-link\" href=\"/#nav-notes\">here</a> to continue.");
        else
            model.addAttribute("errorMessage",errorMessage);

        return "result";
    }
}
