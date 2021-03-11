package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Users;
import com.revature.data.UsersDAO;
import com.revature.utils.HibernateUtil;

public class UsersHibernate implements UsersDAO {

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Users getById(Integer id) {
		Session s = hu.getSession();
		Users u = s.get(Users.class, id);
		s.close();
		return u;
	}

	public Set<Users> getAll() {
		Session s = hu.getSession();
		String query = "FROM users";
		Query<Users> q = s.createQuery(query, Users.class);
		List<Users> usersList = q.getResultList();
		Set<Users> user = new HashSet<>();
		user.addAll(usersList);
		s.close();
		return user;
	}

	public void update(Users t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
	}

	public void delete(Users t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
	}

	public Users add(Users u) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(u);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
		return u;
	}

	public Users getUsersByUsername(String username) {
		Session s = hu.getSession();
		Users u = s.get(Users.class, username);
		s.close();
		return u;
	}

	
}
