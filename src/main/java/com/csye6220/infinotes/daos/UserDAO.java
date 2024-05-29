package com.csye6220.infinotes.daos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.csye6220.infinotes.bucket.AmazonClient;
import com.csye6220.infinotes.pojos.Contact;
import com.csye6220.infinotes.pojos.Note;
import com.csye6220.infinotes.pojos.Role;
import com.csye6220.infinotes.pojos.User;
import com.csye6220.infinotes.services.RoleService;
import com.csye6220.infinotes.utils.FileUploadUtil;
import com.csye6220.infinotes.utils.HibernateUtils;

@Repository
public class UserDAO implements UserDAOInterface{

	@Autowired
	RoleService roleService;
	
	@Autowired
	private AmazonClient amazonClient;
	
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
			session.close();
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
			session.close();
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
			session.close();
		}
		
	}
	
	@Override
	public void saveContact(User user, Contact contact, MultipartFile image) throws IOException {
		try {
//			session = sf.getCurrentSession();
			session = sf.openSession();
			
			begin();
			
			User u = session.get(User.class, user.getId());
			
			Contact c = new Contact();
			
			c.setUser(u);
			
			System.out.println("contact.getContactName() " + contact.getContactName());
			System.out.println("contact.getContactEmail() " + contact.getContactEmail());
			
			c.setContactName(contact.getContactName());
			c.setContactEmail(contact.getContactEmail());
			c.setContactNumber(contact.getContactNumber());
			c.setContactAddress(contact.getContactAddress());
			
			if (image != null && !image.isEmpty()) {
				String fileName = new Date().getTime() + "-" + image.getOriginalFilename().replace(" ", "_");
		    	
		    	this.amazonClient.uploadFile(image, fileName);
		    	
		    	c.setContactImagePath("https://s3.us-east-1.amazonaws.com/infinotesimagebucket/" + fileName);
//		        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
//		        Path uploadDirectory = Paths.get("C://InfinotesImages//");
//
//		        // Ensure directory exists or create it
//		        if (!Files.exists(uploadDirectory)) {
//		            Files.createDirectories(uploadDirectory);
//		        }
//
//		        // Save the file
//		        Path filePath = uploadDirectory.resolve(fileName);
//		        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		        // Set the user's imagePath
//		        c.setContactImagePath("/image/" + fileName);
		        
		    }
			
			u.setUserContact(c);
			System.out.println("c.getContactName() " + c.getContactName());
			System.out.println("c.getContactEmail() " + c.getContactEmail());
			session.persist(c);
			
			commit();
			
		} finally {
//			closeAll();
			session.close();
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
			session.close();
		}

	}

	@Override
	public User findByEmail(String email) {
		try {
//			session = sf.getCurrentSession();
			session = sf.openSession();
			
			begin();
			
			String queryString = "from User u where u.email = :email";
			
			Query query = session.createQuery(queryString, User.class);
			
			query.setParameter("email", email);

			User user = (User) query.uniqueResult();
			
			return user;
			
		} 
//		  catch (Exception e) {
//			    e.printStackTrace(); // Log the exception for debugging
//			}
		finally {
//			closeAll();
			session.close();
		}
	}

	@Override
	public void update(User user) {
		try {
//			session = sf.getCurrentSession();
			session = sf.openSession();
			
			begin();
			System.out.println("update transmission");
			session.merge(user);
			System.out.println("update merged");
			commit();
			System.out.println("update committed");
			
		} finally {
//			closeAll();
			session.close();
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
			session.close();
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
			session.close();
		}
	}
	

	public void printStats() {
	    Statistics stats = sf.getStatistics();
	    System.out.println("Active Connections: " + stats.getConnectCount());
	    // Other stats can also be accessed
	}

}
