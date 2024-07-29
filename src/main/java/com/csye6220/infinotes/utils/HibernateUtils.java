package com.csye6220.infinotes.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.csye6220.infinotes.config.HibernateProperties;
import com.csye6220.infinotes.pojos.Contact;
import com.csye6220.infinotes.pojos.Note;
import com.csye6220.infinotes.pojos.Role;
import com.csye6220.infinotes.pojos.User;

public class HibernateUtils {
	
	private static SessionFactory sessionFactory;
	

	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "");
				settings.put(Environment.USER, "");
				settings.put(Environment.PASS, "");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "update");
				
				configuration.setProperties(settings);
				configuration.addPackage("com.csye6220.infinotes.pojos");
				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(Role.class);
				configuration.addAnnotatedClass(Note.class);
				configuration.addAnnotatedClass(Contact.class);
				
				ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(settings).build();
				
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
	
}
