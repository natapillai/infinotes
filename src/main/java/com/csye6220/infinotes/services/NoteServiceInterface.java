package com.csye6220.infinotes.services;

import com.csye6220.infinotes.pojos.Note;

public interface NoteServiceInterface {

	void addNewNotes(Note note);
	
	Note findNoteByID(int id);
	
	Iterable<Note> getAllNotes(int id);
	
	void updateNotes(Note note);
	
	void deleteNotes(Note note);
	
}
