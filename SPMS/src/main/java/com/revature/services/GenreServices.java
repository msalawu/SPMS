package com.revature.services;

import java.util.Set;

import com.revature.beans.Genre;

public interface GenreServices {

	public Genre getGenreById(Integer id);
    public Set<Genre> getAllGenres();
    public Genre getGenreByName(String name);
}
