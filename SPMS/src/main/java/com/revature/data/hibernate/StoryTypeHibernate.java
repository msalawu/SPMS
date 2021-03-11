package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.StoryType;
import com.revature.data.StoryTypeDAO;
import com.revature.utils.HibernateUtil;

public class StoryTypeHibernate implements StoryTypeDAO {

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public StoryType getById(Integer id) {
		Session s = hu.getSession();
		StoryType st = s.get(StoryType.class, id);
		s.close();
		return st;
	}

	@Override
	public Set<StoryType> getAll() {
		Session s = hu.getSession();
		String query = "FROM story_type";
		Query<StoryType> q = s.createQuery(query, StoryType.class);
		List<StoryType> storyTypeList = q.getResultList();
		Set<StoryType> storyType = new HashSet<>();
		storyType.addAll(storyTypeList);
		s.close();
		return storyType;
	}

	@Override
	public void update(StoryType t) {
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
	public void delete(StoryType t) {
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
	public StoryType add(StoryType st) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(st);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
		return st;
	}

	@Override
	public StoryType getByName(String name) {
		Session s = hu.getSession();
		StoryType st = s.get(StoryType.class, name);
		s.close();
		return st;
	}

}
