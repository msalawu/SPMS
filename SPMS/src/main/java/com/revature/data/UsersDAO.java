package com.revature.data;

import com.revature.beans.Users;

public interface UsersDAO extends GenericDAO<Users>{

	public Users add(Users u);
	public Users getUsersByUsername(String username);
}
