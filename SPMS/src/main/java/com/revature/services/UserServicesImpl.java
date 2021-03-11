package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.beans.Users;
import com.revature.data.DAOFactory;
import com.revature.data.UsersDAO;
import com.revature.exceptions.NonUniqueUsernameException;

public class UserServicesImpl implements UserServices{

	private UsersDAO uDao;
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(UserServicesImpl.class);
	public UserServicesImpl() {
		uDao = DAOFactory.getUsersDAO();
	}
	public Users addUser(Users u) throws NonUniqueUsernameException {
		
		return uDao.add(u);
	}
	public Users getUserById(Integer id) {
		
		return uDao.getById(id);
	}
	public Users getUserByUsername(String username) {
		
		return uDao.getUsersByUsername(username);
	}
	public void updateUser(Users u) {
		if (getUserById(u.getId()) != null)
			uDao.update(u);
		else
			log.debug("This user has been updated in our database.");
	}
	public void deleteUser(Users u) {
		if (getUserById(u.getId()) != null)
			uDao.delete(u);
		else
			log.debug("This particular user does not exist in our database.");
	}
}
