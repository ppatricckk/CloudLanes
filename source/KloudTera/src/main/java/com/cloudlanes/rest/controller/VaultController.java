package com.cloudlanes.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudlanes.db.entities.Policy;
import com.cloudlanes.db.entities.Vault;
import com.cloudlanes.rest.outobject.Status;
import com.cloudlanes.service.VaultService;

@RestController
@RequestMapping(value = "/vaultservices")
public class VaultController {

	@Autowired
	private VaultService vaultservice;
	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Status add(@RequestBody Vault vault) throws Exception {

		if(vaultservice.addVault(vault)){
			return new Status(true,"OK");
		}else{
			return new Status(false,"Not OK"); 
		}

	}


	@RequestMapping(value = "/list", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Vault> list() throws Exception {
		return vaultservice.listVault();


	}

}
