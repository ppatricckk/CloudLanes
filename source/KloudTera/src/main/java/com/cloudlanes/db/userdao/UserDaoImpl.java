package com.cloudlanes.db.userdao;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import org.springframework.stereotype.Repository;

import com.cloudlanes.db.dao.GenericDaoImpl;
import com.cloudlanes.db.entities.User;
import com.cloudlanes.db.policydao.PolicyDaoImpl;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements
		UserDao {
	final static Logger logger = Logger.getLogger(UserDaoImpl.class);
	private static final String CLASS_NAME = UserDaoImpl.class
			.getSimpleName();
	public boolean isUserRegistered(String userName, String password) {
		 String methodName="isUserRegistered()";
	     logger.info("++inside"+methodName+"++");
		String qry = "from User u where u.user_id = ? and u.password = ?";
		Query userquery = currentSession().createQuery(qry);
		userquery.setParameter(0, userName);
		userquery.setParameter(1, password);
		if (userquery.list().size() > 0)
			return true;
		else
			return false;

	}
}
