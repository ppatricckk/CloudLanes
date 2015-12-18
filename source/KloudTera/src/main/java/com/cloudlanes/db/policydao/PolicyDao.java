package com.cloudlanes.db.policydao;

import java.util.List;

import com.cloudlanes.db.dao.GenericDao;
import com.cloudlanes.db.entities.Policy;

public interface PolicyDao extends GenericDao<Policy, Integer> {

	/**
	 * method add policy
	 * 
	 * @param p
	 * @return
	 */
	public boolean addPolicy(Policy p);

	/**
	 * method for remove policy
	 * 
	 * @param p
	 * @return
	 */
	public boolean removePolicy(Policy p);

	/**
	 * method for update policy
	 * 
	 * @param p
	 * @return
	 */
	public boolean updatePolicy(Policy p);

	/**
	 * List policies
	 * 
	 * @param key
	 * @return
	 */
	public List<Policy> listPolicy();

}
