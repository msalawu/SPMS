package com.revature.data;

import com.revature.beans.Author;

public interface AuthorDAO extends GenericDAO<Author>{

	public Author add(Author a);
}
