package com.revature.services;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.beans.Story;
import com.revature.beans.StoryType;
import com.revature.exceptions.NotEnoughPointsException;

public interface StoryServices {

	public Story addStory(Story ss) throws NotEnoughPointsException;
	public void updateStory(Story ss);
	public void deleteStory(Story ss);
	public Story getStoryById(Integer id);
	public Set<Story> getAllStories();
	public Set<Status> getAllStatuses();
	public Status getStatusById(Integer id);
	public Set<StoryType>  getAllStoryType();
	public StoryType getStoryTypeById(Integer id);
	public void addStoryType(StoryType st);
	public Set<Genre>  getAllGenre();
	public Genre getGenreById(Integer id);
	public Set<Role>  getAllRole();
	public Role getRoleById(Integer id);
}
