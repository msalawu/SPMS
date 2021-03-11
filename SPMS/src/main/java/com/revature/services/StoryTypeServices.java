package com.revature.services;

import java.util.Set;

import com.revature.beans.StoryType;

public interface StoryTypeServices {

	 public StoryType getStoryTypeById(Integer id);
	    public Set<StoryType> getAllStoryTypes();
	    public StoryType getStoryTypeByName(String name);
}
