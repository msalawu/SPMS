package com.revature.services;

import com.revature.beans.Users;
import com.revature.exceptions.NonUniqueUsernameException;

public interface UserServices {

	 	public Users addUser(Users u) throws NonUniqueUsernameException;
	    public Users getUserById(Integer id);
	    public Users getUserByUsername(String username);
	    public void updateUser(Users u);
	    public void deleteUser(Users u);
	   // public Integer remainingPoints(Users u);
}
