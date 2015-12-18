package com.cloudlanes.db.alertdao;

import com.cloudlanes.db.dao.GenericDao;
import com.cloudlanes.db.entities.AlertRecommendation;

public interface AlertRecommendationDAO extends GenericDao<AlertRecommendation, Integer>{
	/**
	 * method add Recommendation
	 *
	 * @param alert
	 * @return boolean
	 */
	public boolean addRecommendation(AlertRecommendation recommendation);

	/**
	 * method for remove Recommendation
	 *
	 * @param alert
	 * @return boolean
	 */
	public boolean removeRecommendation(AlertRecommendation recommendation);

	/**
	 * method for update Recommendation
	 *
	 * @param alert
	 * @return boolean
	 */
	public boolean updateRecommendation(AlertRecommendation recommendation);
	
	/**
	 * Get Recommendation
	 * identified by the code
	 * @param key
	 * @return list
	 */
	public AlertRecommendation getRecommendation(Double code);
}
