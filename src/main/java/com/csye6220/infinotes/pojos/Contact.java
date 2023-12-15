package com.csye6220.infinotes.pojos;

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
@Table(name="contact")
public class Contact {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="contactId")
	private int contactId;
	
	@Column(name="contactName")
	private String contactName;
	
	@Column(name="contactEmail")
	private String contactEmail;
	
	@Column(name="contactNumber")
	private String contactNumber;
	
	@Column(name="contactAddress")
	private String contactAddress;
	
	@Column(name="contactImagePath")
	private String contactImagePath;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "id")
	private User user;

	
	public Contact(String contactName, String contactEmail, String contactNumber, String contactAddress,
			String contactImagePath) {
		super();
		this.contactName = contactName;
		this.contactEmail = contactEmail;
		this.contactNumber = contactNumber;
		this.contactAddress = contactAddress;
		this.contactImagePath = contactImagePath;
	}

	public Contact() {
	}

	
	public int getContactId() {
		return contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContactImagePath() {
		return contactImagePath;
	}

	public void setContactImagePath(String contactImagePath) {
		this.contactImagePath = contactImagePath;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", contactName=" + contactName + ", contactEmail=" + contactEmail
				+ ", contactNumber=" + contactNumber + ", contactAddress=" + contactAddress + ", contactImagePath="
				+ contactImagePath + "]";
	}
	
	
	
}
