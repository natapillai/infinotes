package com.csye6220.infinotes.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csye6220.infinotes.pojos.Note;
import com.csye6220.infinotes.pojos.User;
import com.csye6220.infinotes.services.NoteService;
import com.csye6220.infinotes.services.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class NoteController {

	@Autowired
	UserService userService;
	
	@Autowired
	NoteService noteService;
	
	@GetMapping("/user/notes")
	public String handleAllNotes(HttpSession session, Model model) {
		
		Integer userId = getUserIdFromSession(session);
		
		if (userId != null) {
            User user = userService.findUserByID(userId); // Method to find user by ID
            model.addAttribute("user", user);
        }
		
		List<Note> notes = new ArrayList<>();
		
		noteService.getAllNotes(userId).forEach(notes::add);
		
		model.addAttribute("notes", notes);
		
		return "allnotes-view";
	}
	
	
	@GetMapping("/user/note/new")
	public String newNote(HttpSession session, Model model) {
		
		Integer userId = getUserIdFromSession(session);
		
		if (userId != null) {
            User user = userService.findUserByID(userId); // Method to find user by ID
            model.addAttribute("user", user);
        }
		
		Note note = new Note();
		model.addAttribute("note",note);
		
		System.out.println("GetMapping User Note");
		
		return "createNote-view";
	}
	
	@PostMapping("/user/note/new")
	public String handleNewNote(@ModelAttribute("note") Note note, HttpSession session, Model model) {
		System.out.println("PostMapping User Note");
		String noteTitle = note.getNoteTitle();
		String noteContent = note.getNoteContent();
		
		System.out.println("noteTitle  " + noteTitle);
		System.out.println("noteContent  " + noteContent);
		
		Integer userId = getUserIdFromSession(session);
		
        User user = userService.findUserByID(userId);
		System.out.println("Before User Service saveNote");
		userService.saveNote(user, note);
		System.out.println("Before User Service saveNote");
		return "redirect:/user/notes";
	}
	
    @GetMapping("/infinotes/user/note/edit/{id}")
    public String editNote(@PathVariable("id") int noteId, Model model, HttpSession session) {
    	
		Integer userId = getUserIdFromSession(session);
		
		if (userId != null) {
            User user = userService.findUserByID(userId); // Method to find user by ID
            model.addAttribute("user", user);
        }
    	
        Note note = noteService.findNoteByID(noteId);
        model.addAttribute("note", note);
        return "editNote-view"; // Name of the Thymeleaf template for editing a note
    }
	
    @PostMapping("/user/note/edit")
    public String handleNoteEdit(@RequestParam("noteId") int noteId,
                                 @RequestParam("noteTitle") String noteTitle,
                                 @RequestParam("noteContent") String noteContent) {
        Note note = noteService.findNoteByID(noteId);

//        if (note != null) {
//            note.setNoteTitle(noteTitle);
//            note.setNoteContent(noteContent);
//            noteService.updateNotes(note); // Assuming save method handles both create and update
//        }
        
        if (note != null) {
            boolean isChanged = false;

            if (!note.getNoteTitle().equals(noteTitle)) {
            	note.setNoteTitle(noteTitle);
                isChanged = true;
            }

            if (!note.getNoteContent().equals(noteContent)) {
            	note.setNoteContent(noteContent);
                isChanged = true;
            }

            if (isChanged) {
                noteService.updateNotes(note);
            }
        }

        return "redirect:/user/notes"; // Redirect to the page where notes are listed
    }
    
    @PostMapping("/infinotes/user/note/delete/{id}")
    public String deleteNote(@PathVariable("id") int noteId) {
    	Note note = noteService.findNoteByID(noteId);
        noteService.deleteNotes(note);
        return "redirect:/user/notes"; // Redirect to the page where notes are listed
    }
	
    private Integer getUserIdFromSession(HttpSession session) {
        // Implementation to retrieve userId from session
        return (Integer) session.getAttribute("userId");
    }
}
