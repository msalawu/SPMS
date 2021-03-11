package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Editor;
import com.revature.data.EditorDAO;
import com.revature.utils.HibernateUtil;

public class EditorHibernate implements EditorDAO {

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Editor getById(Integer id) {
		Session s = hu.getSession();
		Editor e = s.get(Editor.class, id);
		s.close();
		return e;
	}

	@Override
	public Set<Editor> getAll() {
		Session s = hu.getSession();
		String query = "FROM editor";
		Query<Editor> q = s.createQuery(query, Editor.class);
		List<Editor> editorsList = q.getResultList();
		Set<Editor> editors = new HashSet<>();
		editors.addAll(editorsList);
		s.close();
		return editors;
	}

	@Override
	public void update(Editor t) {
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
	public void delete(Editor t) {
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
	public Editor add(Editor ee) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(ee);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
		return ee;
	}

	@Override
	public Editor getByUsername(String username) {
		Session s = hu.getSession();
		Editor e =s.get(Editor.class, username);
		s.close();
		return e;
	}

}
