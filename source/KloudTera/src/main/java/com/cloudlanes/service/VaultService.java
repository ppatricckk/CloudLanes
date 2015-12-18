package com.cloudlanes.service;

import java.util.List;

import com.cloudlanes.db.entities.Policy;
import com.cloudlanes.db.entities.Vault;

public interface VaultService {

	boolean addVault(Vault vault);
	public List<Vault> listVault();



}
