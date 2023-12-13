package com.csye6220.infinotes.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.csye6220.infinotes.pojos.Note;
import com.csye6220.infinotes.pojos.Role;
import com.csye6220.infinotes.utils.HibernateUtils;

@Repository
public class NoteDAO implements NoteDAOInterface{

	private SessionFactory sf = HibernateUtils.getSessionFactory();
	
	private Session session = null;
	
	public void begin() {
		session.beginTransaction();
	}
	
	public void commit() {
		session.getTransaction().commit();
	}
	
	public void closeAll() {
		session.close();
		sf.close();
	}
	
	@Override
	public void saveNote(Note note) {
		
		try {
			session = sf.openSession();
			
			begin();
			
			session.persist(note);
			
			commit();
		} finally {
			// TODO: handle finally clause
		}
		
	}

	@Override
	public Note findNotebyID(int id) {
		
		try {
			session = sf.openSession();
			
			begin();
			
			String queryString = "from Note n where n.noteId = :id";
			
			Query query = session.createQuery(queryString, Note.class);
			
			query.setParameter("id", id);
			
			Note note = (Note) query.uniqueResult();
			
			return note;
			
		} finally {
			
		}
		
		
	}

	@Override
	public Iterable getNotes(int id) {
		
		try {
			session = sf.openSession();
			
			begin();
			
			String queryString = "from Note n where n.user.id = :id";
			
			Query query = session.createQuery(queryString, Note.class);
			
			query.setParameter("id", id);
			
			List<Note> notes = query.list();
			
			return notes;
			
		} finally {
			
		}
	}

	@Override
	public void updateNote(Note note) {
		
		try {
			session = sf.openSession();	
			
			begin();
			
			session.merge(note);
			
			commit();
		} finally {
			// TODO: handle finally clause
		}
		
	}

	@Override
	public void deleteNote(Note note) {
		
		try {
			session = sf.openSession();
			
			begin();
			
			session.remove(note);
			
			commit();
		} finally {
			// TODO: handle finally clause
		}
		
	}

}
