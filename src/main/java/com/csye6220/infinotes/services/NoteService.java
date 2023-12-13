package com.csye6220.infinotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csye6220.infinotes.daos.NoteDAOInterface;
import com.csye6220.infinotes.daos.RoleDAO;
import com.csye6220.infinotes.daos.RoleDAOInterface;
import com.csye6220.infinotes.pojos.Note;

@Service
public class NoteService implements NoteServiceInterface{

	@Autowired
	private NoteDAOInterface noteDAO;
	
	@Override
	public void addNewNotes(Note note) {
		noteDAO.saveNote(note);
	}

	@Override
	public Note findNoteByID(int id) {
		return noteDAO.findNotebyID(id);
	}

	@Override
	public Iterable<Note> getAllNotes(int id) {
		return noteDAO.getNotes(id);
	}

	@Override
	public void updateNotes(Note note) {
		noteDAO.updateNote(note);
	}

	@Override
	public void deleteNotes(Note note) {
		noteDAO.deleteNote(note);
	}

}
