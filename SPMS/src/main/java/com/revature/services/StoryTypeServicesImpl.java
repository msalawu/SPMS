package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.StoryType;
import com.revature.data.DAOFactory;
import com.revature.data.StoryTypeDAO;

public class StoryTypeServicesImpl implements StoryTypeServices{

	private StoryTypeDAO sstDao;
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(StoryTypeServicesImpl.class);
	public StoryTypeServicesImpl() {
		sstDao = DAOFactory.getStoryTypeDAO();
	}
	public StoryType getStoryTypeById(Integer id) {
		
		return sstDao.getById(id);
	}
	public Set<StoryType> getAllStoryTypes() {
		
		return sstDao.getAll();
	}
	public StoryType getStoryTypeByName(String name) {
		
		return sstDao.getByName(name);
	}
}
