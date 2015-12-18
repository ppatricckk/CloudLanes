package com.cloudlanes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudlanes.db.userdao.UserDao;


@Service
public class LoginServiceImpl implements LoginService {
@Autowired 
UserDao userdao;
	@Override
	@Transactional
	public boolean authenticate(String user_id, String password) {
		// TODO Auto-generated method stub
		return userdao.isUserRegistered(user_id,password);
	}

	
	
}
