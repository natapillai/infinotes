package com.csye6220.infinotes.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.csye6220.infinotes.pojos.Role;
import com.csye6220.infinotes.pojos.User;

public class DAO {

	SessionFactory sf = null;
	Session session = null;
	
	public void init() {
		sf = new Configuration()
				.configure()
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class)
				.buildSessionFactory();
		
		session = sf.getCurrentSession();
	}
	
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
	
}
