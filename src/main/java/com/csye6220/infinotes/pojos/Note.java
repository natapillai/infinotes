package com.csye6220.infinotes.pojos;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="note")
//@Component
public class Note {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="noteId")
	private int noteId;
	
	@Column(name="noteTitle")
	private String noteTitle;
	
	@Lob
	@Column(name="noteContent")
	private String noteContent;
	
	@CreationTimestamp
	@Column(name="noteCreatedDate", updatable = false)
	private LocalDateTime noteCreatedDate;
	
	@UpdateTimestamp
	@Column(name="noteLastModifiedDate")
	private LocalDateTime noteLastModifiedDate;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "id")
	private User user;
	
	
	public Note(String noteTitle, String noteContent, LocalDateTime noteCreatedDate,
			LocalDateTime noteLastModifiedDate) {
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.noteCreatedDate = noteCreatedDate;
		this.noteLastModifiedDate = noteLastModifiedDate;
	}
	
	

	public Note() {
	}

	


	public int getNoteId() {
		return noteId;
	}



	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public LocalDateTime getNoteCreatedDate() {
		return noteCreatedDate;
	}

	public void setNoteCreatedDate(LocalDateTime noteCreatedDate) {
		this.noteCreatedDate = noteCreatedDate;
	}

	public LocalDateTime getNoteLastModifiedDate() {
		return noteLastModifiedDate;
	}

	public void setNoteLastModifiedDate(LocalDateTime noteLastModifiedDate) {
		this.noteLastModifiedDate = noteLastModifiedDate;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
