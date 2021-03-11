package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Status;
import com.revature.data.StatusDAO;
import com.revature.utils.HibernateUtil;

public class StatusHibernate implements StatusDAO {

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Status getById(Integer id) {
		Session s = hu.getSession();
		Status stat = s.get(Status.class, id);
		s.close();
		return stat;
	}

	@Override
	public Set<Status> getAll() {
		Session s = hu.getSession();
		String query = "FROM status";
		Query<Status> q = s.createQuery(query, Status.class);
		List<Status> statusList = q.getResultList();
		Set<Status> status = new HashSet<>();
		status.addAll(statusList);
		s.close();
		return status;
	}

	@Override
	public void update(Status t) {
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
	public void delete(Status t) {
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
	public Status add(Status stat) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(stat);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
		return stat;
	}

	
}
