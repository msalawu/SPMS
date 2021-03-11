package com.revature.data;

import com.revature.beans.StoryType;

public interface StoryTypeDAO extends GenericDAO<StoryType>{

	public StoryType add(StoryType st);
	public StoryType getByName(String name);
}
