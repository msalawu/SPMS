package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Rejections;
import com.revature.data.RejectionsDAO;
import com.revature.utils.HibernateUtil;

public class RejectionsHibernate implements RejectionsDAO {

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Rejections getById(Integer id) {
		Session s = hu.getSession();
		Rejections rej = s.get(Rejections.class, id);
		s.close();
		return rej;
	}

	@Override
	public Set<Rejections> getAll() {
		Session s = hu.getSession();
		String query = "FROM rejections";
		Query<Rejections> q = s.createQuery(query, Rejections.class);
		List<Rejections> rejectionsList = q.getResultList();
		Set<Rejections> rej = new HashSet<>();
		rej.addAll(rejectionsList);
		s.close();
		return rej;
	}

	@Override
	public void update(Rejections t) {
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
	public void delete(Rejections t) {
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
	public Rejections add(Rejections rej) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(rej);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
		return rej;
	}

}
