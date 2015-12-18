package com.cloudlanes.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudlanes.db.entities.User;
import com.cloudlanes.rest.outobject.Status;
import com.cloudlanes.service.LoginService;

@RestController
@RequestMapping(value = "/loginService")
public class LoginController {
	@Autowired
	private LoginService loginservice;
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Status checkUser(@RequestBody User user) throws Exception {
		System.out.println("request mapped");
		if(loginservice.authenticate(user.getUser_id(),user.getPassword())){
			return new Status(true,"OK");
		}else{
			return new Status(false,"Not OK"); 
		}

	}
}
