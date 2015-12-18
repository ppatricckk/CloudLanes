package com.cloudlanes.db.vaultdao;

import java.util.List;

import com.cloudlanes.db.dao.GenericDao;
import com.cloudlanes.db.entities.Policy;
import com.cloudlanes.db.entities.Vault;

public interface VaultDao extends GenericDao<Vault, Integer> {

	/**
	 * method add Vault
	 * 
	 * @param p
	 * @return
	 */
	public boolean createVault(Vault v);

	/**
	 * method for remove vault
	 * 
	 * @param p
	 * @return
	 */
	public boolean removeVault(Vault v);

	/**
	 * method for update Vault
	 * 
	 * @param p
	 * @return
	 */
	public boolean updateVault(Vault v);

	/**
	 * find Vault by vaultType
	 * 
	 * @param key
	 * @return
	 */
	/**
	 * List policies
	 * 
	 * @param key
	 * @return
	 */
	public List<Vault> listVault();



}
