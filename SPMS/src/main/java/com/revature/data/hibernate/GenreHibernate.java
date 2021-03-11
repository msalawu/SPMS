package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Genre;
import com.revature.data.GenreDAO;
import com.revature.utils.HibernateUtil;

public class GenreHibernate implements GenreDAO {

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Genre getById(Integer id) {
		Session s = hu.getSession();
		Genre g = s.get(Genre.class, id);
		s.close();
		return g;
	}

	@Override
	public Set<Genre> getAll() {
		Session s = hu.getSession();
		String query = "FROM genre";
		Query<Genre> q = s.createQuery(query, Genre.class);
		List<Genre> genreList = q.getResultList();
		Set<Genre> genre = new HashSet<>();
		genre.addAll(genreList);
		s.close();
		return genre;
	}

	@Override
	public void update(Genre t) {
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

	@Override
	public void delete(Genre t) {
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

	@Override
	public Genre add(Genre g) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(g);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
		return g;
	}

	@Override
	public Genre getGenreByName(String name) {
		Session s = hu.getSession();
		Genre g = s.get(Genre.class, name);
		s.close();
		return g;
	}

}
