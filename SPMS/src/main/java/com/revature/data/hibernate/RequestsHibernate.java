package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Requests;
import com.revature.data.RequestsDAO;
import com.revature.utils.HibernateUtil;

public class RequestsHibernate implements RequestsDAO {

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	
	@Override
	public Requests getById(Integer id) {
		Session s = hu.getSession();
		Requests req = s.get(Requests.class, id);
		s.close();
		return req;
	}

	@Override
	public Set<Requests> getAll() {
		Session s = hu.getSession();
		String query = "FROM requests";
		Query<Requests> q = s.createQuery(query, Requests.class);
		List<Requests> requestList = q.getResultList();
		Set<Requests> req = new HashSet<>();
		req.addAll(requestList);
		s.close();
		return req;
	}

	@Override
	public void update(Requests t) {
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
	public void delete(Requests t) {
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
	public Requests add(Requests req) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(req);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
		return req;
	}

}
