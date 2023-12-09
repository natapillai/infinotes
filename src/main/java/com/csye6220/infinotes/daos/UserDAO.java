package com.csye6220.infinotes.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.csye6220.infinotes.pojos.Note;
import com.csye6220.infinotes.pojos.Role;
import com.csye6220.infinotes.pojos.User;
import com.csye6220.infinotes.services.RoleService;
import com.csye6220.infinotes.utils.HibernateUtils;

@Repository
public class UserDAO implements UserDAOInterface{

	@Autowired
	RoleService roleService;
	
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
	public void saveUser(User user) {
		
		try {
			
//			session = sf.getCurrentSession();
			session = sf.openSession();
			
			begin();
			
			session.persist(user);
			
			commit();
			
		} finally {
			
//			closeAll();
			
		}
		
	}
	
	@Override
	public void saveRole(User user) {
		
		
		try {
//			session = sf.getCurrentSession();
			session = sf.openSession();
			
			begin();
			
			User u = session.get(User.class, user.getId());
			
			Role role = new Role();
			
			role.setRoleName("User");
			role.setUser(u);
			
			u.addUserRole(role);
			
			session.persist(role);
			
			commit();
			
		} finally {
//			closeAll();
		}
		
	}
	
	
	@Override
	public void saveNote(User user, Note note) {
		try {
//			session = sf.getCurrentSession();
			session = sf.openSession();
			
			begin();
			
			User u = session.get(User.class, user.getId());
			
			Note n = new Note();
			
			n.setUser(u);
			
			n.setNoteTitle(note.getNoteTitle());
			n.setNoteContent(note.getNoteContent());
			n.setNoteCreatedDate(note.getNoteCreatedDate());
			n.setNoteLastModifiedDate(note.getNoteLastModifiedDate());
			
			
			u.setUserNote(n);
			
			session.persist(n);
			
			commit();
			
		} finally {
//			closeAll();
		}
		
		
		
	}
	

	@Override
	public User findByID(int id) {

		try {
//			session = sf.getCurrentSession();
			session = sf.openSession();
			
			begin();
			
			String queryString = "from User u where u.id = :id";
			
			Query query = session.createQuery(queryString, User.class);
			
			query.setParameter("id", id);
			
			User user = (User) query.uniqueResult();
			
			return user;
			
		} finally {
//			closeAll();
		}

	}

	@Override
	public User findByEmail(String email) {
		System.out.println("DAO find 1");
		try {
			System.out.println("DAO find 2");
//			session = sf.getCurrentSession();
			session = sf.openSession();
			
			System.out.println("DAO find 3");
			
			begin();
//			session.beginTransaction();
			System.out.println("DAO find 4");
			
			String queryString = "from User u where u.email = :email";
			
			Query query = session.createQuery(queryString, User.class);
			
			System.out.println("DAO find 5");
			
			query.setParameter("email", email);
			
			System.out.println("DAO find 6");
			User user = (User) query.uniqueResult();
			
			System.out.println("DAO find 7");
			
			System.out.println("userDao findbyemail");
			
			return user;
			
		} 
//		  catch (Exception e) {
//			    e.printStackTrace(); // Log the exception for debugging
//			}
		finally {
//			closeAll();
		}
	}

	@Override
	public void update(User user) {
		try {
//			session = sf.getCurrentSession();
			session = sf.openSession();
			
			begin();
			
			session.merge(user);
			
			commit();
			
		} finally {
//			closeAll();
		}
		
	}

	@Override
	public Iterable getUsers() {
		try {
//			session = sf.getCurrentSession();
			session = sf.openSession();
			
			begin();
			
			String queryString = "from User";
			
			Query query = session.createQuery(queryString, User.class);
			
			List<User> userList = query.list();
			
			return userList;
			
		} finally {
//			closeAll();
		}
	}

	@Override
	public void add(User user) {
		saveUser(user);
	}

	@Override
	public void delete(User user) {
		try {
//			session = sf.getCurrentSession();
			session = sf.openSession();
			
			begin();
			
			session.remove(user);
			
			commit();
			
		} finally {
//			closeAll();
		}
	}

}
