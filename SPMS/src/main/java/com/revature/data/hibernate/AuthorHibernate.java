package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Author;
import com.revature.data.AuthorDAO;
import com.revature.utils.HibernateUtil;

public class AuthorHibernate implements AuthorDAO{

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Author getById(Integer id) {
		Session s = hu.getSession();
		Author a = s.get(Author.class, id);
		s.close();
		return a;
	}

	public Set<Author> getAll() {
		Session s = hu.getSession();
		String query = "FROM author";
		Query<Author> q = s.createQuery(query, Author.class);
		List<Author> authorsList = q.getResultList();
		Set<Author> authors = new HashSet<>();
		authors.addAll(authorsList);
		s.close();
		return authors;
	}

	public void update(Author t) {
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

	public void delete(Author t) {
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

	public Author add(Author a) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(a);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
		return a;
	}
}
