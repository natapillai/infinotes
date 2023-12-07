package com.csye6220.infinotes.pojos;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="note")
@Component
public class Note {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="noteId")
	private int noteId;
	
	@Column(name="noteTitle")
	private String noteTitle;
	
	@Column(name="noteContent")
	private String noteContent;
	
	@Column(name="noteCreatedDate")
	private LocalDateTime noteCreatedDate;
	
	@Column(name="noteLastModifiedDate")
	private LocalDateTime noteLastModifiedDate;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "id")
	private User user;
	
//	private User user;
	//do i need to assign each note to a specific user or just have each user have a list of all notes
	
	public Note(String noteTitle, String noteContent, LocalDateTime noteCreatedDate,
			LocalDateTime noteLastModifiedDate) {
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.noteCreatedDate = noteCreatedDate;
		this.noteLastModifiedDate = noteLastModifiedDate;
	}
	
	

	public Note() {
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
	
	
	
}
