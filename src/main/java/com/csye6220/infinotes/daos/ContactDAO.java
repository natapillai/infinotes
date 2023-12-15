package com.csye6220.infinotes.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.csye6220.infinotes.pojos.Contact;
import com.csye6220.infinotes.utils.HibernateUtils;

@Repository
public class ContactDAO implements ContactDAOInterface{

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
	public void saveContact(Contact contact) {
		
		try {
			session = sf.openSession();
			
			begin();
			
			session.persist(contact);
			
			commit();
		} finally {
			session.close();
		}
		
	}

	@Override
	public Contact findContactbyID(int id) {
		
		try {
			session = sf.openSession();
			
			begin();
			
			String queryString = "from Contact c where c.contactId = :id";
			
			Query query = session.createQuery(queryString, Contact.class);
			
			query.setParameter("id", id);
			
			Contact contact = (Contact) query.uniqueResult();
			
			return contact;
			
		} finally {
			session.close();
		}
		
	}

	@Override
	public Iterable getContacts(int id) {

		try {
			session = sf.openSession();
			
			begin();
			
			String queryString = "from Contact c where c.user.id = :id";
			
			Query query = session.createQuery(queryString, Contact.class);
			
			query.setParameter("id", id);
			
			List<Contact> contacts = query.list();
			
			return contacts;
			
		} finally {
			session.close();
		}
		
	}

	@Override
	public void updateContact(Contact contact) {

		try {
			session = sf.openSession();	
			
			begin();
			
			session.merge(contact);
			
			commit();
		} finally {
			session.close();
		}
		
	}

	@Override
	public void deleteContact(Contact contact) {

		try {
			session = sf.openSession();
			
			begin();
			
			session.remove(contact);
			
			commit();
		} finally {
			session.close();
		}
		
	}

}
