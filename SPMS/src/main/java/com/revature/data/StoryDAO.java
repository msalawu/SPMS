package com.revature.data;

import com.revature.beans.Story;

public interface StoryDAO extends GenericDAO<Story>{

	public Story add(Story ss);
	
}
