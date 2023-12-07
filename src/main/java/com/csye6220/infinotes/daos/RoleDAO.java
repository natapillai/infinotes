package com.csye6220.infinotes.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.csye6220.infinotes.pojos.Role;
import com.csye6220.infinotes.pojos.User;
import com.csye6220.infinotes.utils.HibernateUtils;

@Repository
public class RoleDAO implements RoleDAOInterface{
	
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
	public void addRole(Role role) {
		
		try {
			session = sf.getCurrentSession();
			
			begin();
			
			session.persist(role);
			
			commit();
			
		} finally {
			
			closeAll();
			
		}
		
	}

	@Override
	public Role findRoleByID(int id) {
		
		try {
			session = sf.getCurrentSession();
			
			begin();
			
			String queryString = "from Role r where r.id = :id";
			
			Query query = session.createQuery(queryString, Role.class);
			
			query.setParameter("id", id);
			
			Role role = (Role) query.uniqueResult();
			
			return role;
			
		} finally {
			
			closeAll();
			
		}
		
	}

	@Override
	public Iterable getRoles(int id) {
		
		try {
			session = sf.getCurrentSession();
			
			begin();
			
			String queryString = "from Role r where r.user.id = :id";
			
			Query query = session.createQuery(queryString, Role.class);
			
			query.setParameter("id", id);
			
			List<Role> roles = query.list();
			
			return roles;
			
		} finally {

			closeAll();
			
		}
		
	}

	@Override
	public void updateRole(Role role) {
		
		try {
			session = sf.getCurrentSession();
			
			begin();
			
			session.merge(role);
			
			commit();
			
		} finally {
			
			closeAll();
			
		}
		
	}

	@Override
	public void deleteRole(Role role) {
		
		try {
			session = sf.getCurrentSession();
			
			begin();
			
			session.remove(role);
			
			commit();
			
		} finally {

			closeAll();
			
		}
		
	}

}
