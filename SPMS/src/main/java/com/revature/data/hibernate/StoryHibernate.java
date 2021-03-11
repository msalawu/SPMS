package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Story;
import com.revature.data.StoryDAO;
import com.revature.utils.HibernateUtil;

public class StoryHibernate implements StoryDAO {

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Story getById(Integer id) {
		Session s = hu.getSession();
		Story ss = s.get(Story.class, id);
		s.close();
		return ss;
	}

	@Override
	public Set<Story> getAll() {
		Session s = hu.getSession();
		String query = "FROM story";
		Query<Story> q = s.createQuery(query, Story.class);
		List<Story> storyList = q.getResultList();
		Set<Story> story = new HashSet<>();
		story.addAll(storyList);
		s.close();
		return story;
	}

	@Override
	public void update(Story t) {
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
	public void delete(Story t) {
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
	public Story add(Story ss) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(ss);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
		return ss;
	}
	
}
