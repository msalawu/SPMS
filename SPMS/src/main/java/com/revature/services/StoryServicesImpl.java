package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Genre;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.beans.Story;
import com.revature.beans.StoryType;
import com.revature.data.DAOFactory;
import com.revature.data.GenreDAO;
import com.revature.data.RoleDAO;
import com.revature.data.StatusDAO;
import com.revature.data.StoryDAO;
import com.revature.data.StoryTypeDAO;
import com.revature.exceptions.NotEnoughPointsException;

public class StoryServicesImpl implements StoryServices{

	private StoryDAO ssDao;
	private StatusDAO statusDao;
	private StoryTypeDAO stDao;
	private GenreDAO gDao;
	private RoleDAO rDao;
	
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(StoryServicesImpl.class);
	
	public StoryServicesImpl() {
		ssDao = DAOFactory.getStoryDAO();
		statusDao = DAOFactory.getStatusDAO();
		stDao = DAOFactory.getStoryTypeDAO();
		gDao = DAOFactory.getGenreDAO();
		rDao = DAOFactory.getRoleDAO();
	}

	public Story addStory(Story ss) throws NotEnoughPointsException {
		
		return ssDao.add(ss);
	}

	public void updateStory(Story ss) {
		if (getStoryById(ss.getId()) != null)
			ssDao.update(ss);
		else
			log.debug("This user has been updated in our database.");
	}

	public Story getStoryhById(Integer id) {
		
		return ssDao.getById(id);
	}

	public Set<Story> getAllStories() {
		
		return ssDao.getAll();
	}

	@Override
	public Story getStoryById(Integer id) {
		
		return ssDao.getById(id);
	}

	@Override
	public void deleteStory(Story ss) {
		if (getStoryById(ss.getId()) != null)
			ssDao.delete(ss);
		else
			log.debug("This particular user does not exist in our database.");
	}

	@Override
	public Set<Status> getAllStatuses() {
		
		return statusDao.getAll();
	}

	@Override
	public Status getStatusById(Integer id) {
		
		return statusDao.getById(id);
	}

	@Override
	public Set<StoryType> getAllStoryType() {
		
		return stDao.getAll();
	}

	@Override
	public StoryType getStoryTypeById(Integer id) {
		
		return stDao.getById(id);
	}

	@Override
	public void addStoryType(StoryType st) {
		stDao.add(st);
		
	}

	@Override
	public Set<Genre> getAllGenre() {
		
		return gDao.getAll();
	}

	@Override
	public Genre getGenreById(Integer id) {
		
		return gDao.getById(id);
	}

	@Override
	public Set<Role> getAllRole() {
		
		return rDao.getAll();
	}

	@Override
	public Role getRoleById(Integer id) {
		
		return rDao.getById(id);
	}
}
