package com.cloudlanes.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudlanes.db.entities.Policy;
import com.cloudlanes.rest.outobject.Status;
import com.cloudlanes.service.PolicyService;

@RestController
@RequestMapping(value = "/policyservices")
public class PolicyController {

	@Autowired
	private PolicyService policyservice;

	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Status add(@RequestBody Policy policy) throws Exception {
		if(policyservice.add(policy)){
			return new Status(true,"OK");
		}else{
			return new Status(false,"Not OK"); 
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Status update(@RequestBody Policy policy) throws Exception {

		if(policyservice.update(policy)){
			return new Status(true,"OK");
		}else{
			return new Status(false,"Not OK"); 
		}

	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Status delete(@RequestBody Policy policy) throws Exception {

		if(policyservice.delete(policy)){
			return new Status(true,"OK");
		}else{
			return new Status(false,"Not OK"); 
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Policy> list() throws Exception {
		return policyservice.listPolicy();


	}


}
