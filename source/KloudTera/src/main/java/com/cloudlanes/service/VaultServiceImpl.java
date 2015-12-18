package com.cloudlanes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudlanes.db.entities.Vault;
import com.cloudlanes.db.vaultdao.VaultDao;

@Service
public class VaultServiceImpl implements VaultService {
	@Autowired
	VaultDao vaultdao;
	@Override
	@Transactional
	public boolean addVault(Vault vault) {
		 vaultdao.add(vault);
		 return true;
	}
	@Override
	@Transactional
	public List<Vault> listVault() {
		// TODO Auto-generated method stub
		return vaultdao.listVault();
	}


}
