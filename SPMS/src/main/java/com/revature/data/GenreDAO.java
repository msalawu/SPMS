package com.revature.data;

import com.revature.beans.Genre;

public interface GenreDAO extends GenericDAO<Genre>{

	public Genre add(Genre g);
	public Genre getGenreByName (String name);
}
