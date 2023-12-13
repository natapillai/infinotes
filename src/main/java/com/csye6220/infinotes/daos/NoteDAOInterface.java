package com.csye6220.infinotes.daos;

import org.springframework.stereotype.Repository;

import com.csye6220.infinotes.pojos.Note;

@Repository
public interface NoteDAOInterface {

	public void saveNote(Note note);
	
	public Note findNotebyID(int id);
	
	Iterable getNotes(int id);
	
	public void updateNote(Note note);
	
	public void deleteNote(Note note);
	
}
