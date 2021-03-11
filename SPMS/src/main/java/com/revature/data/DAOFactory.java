package com.revature.data;

import com.revature.data.hibernate.*;

public class DAOFactory {

	public AuthorDAO getAuthorDAO() {
		return new AuthorHibernate();
	}
	
	public static UsersDAO getUsersDAO() {
		return new UsersHibernate();
	}
	
	public static EditorDAO getEditorDAO() {
		return new EditorHibernate();
	}
	
	public static GenreDAO getGenreDAO() {
		return new GenreHibernate();
	}
	
	public static RejectionsDAO getRejectionsDAO() {
		return new RejectionsHibernate();
	}
	
	public static RequestsDAO getRequestsDAO() {
		return new RequestsHibernate();
	}
	
	public static RoleDAO getRoleDAO() {
		return new RoleHibernate();
	}
	
	public static StatusDAO getStatusDAO() {
		return new StatusHibernate();
	}
	
	public static StoryDAO getStoryDAO() {
		return new StoryHibernate();
	}
	
	public static StoryTypeDAO getStoryTypeDAO() {
		return new StoryTypeHibernate();
	}
}
