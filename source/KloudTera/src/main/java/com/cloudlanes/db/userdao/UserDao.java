package com.cloudlanes.db.userdao;

import com.cloudlanes.db.dao.GenericDao;
import com.cloudlanes.db.entities.User;

public interface UserDao extends GenericDao<User, Integer>{
	/**
	 * method for user Login authentication
	 * @param userName
	 * @param password
	 * @return boolean 
	 */
	  public boolean isUserRegistered(String userName, String password);

}
