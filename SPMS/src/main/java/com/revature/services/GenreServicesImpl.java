package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Genre;
import com.revature.data.DAOFactory;
import com.revature.data.GenreDAO;

public class GenreServicesImpl implements GenreServices{

	private GenreDAO genreDao;
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(GenreServicesImpl.class);
	public GenreServicesImpl() {
		genreDao = DAOFactory.getGenreDAO();
	}

	public Genre getGenreById(Integer id) {
		
		return genreDao.getById(id);
	}

	public Set<Genre> getAllGenres() {
		
		return genreDao.getAll();
	}

	public Genre getGenreByName(String name) {
		
		return genreDao.getGenreByName(name);
	}
}
