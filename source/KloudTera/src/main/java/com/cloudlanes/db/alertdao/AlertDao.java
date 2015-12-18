package com.cloudlanes.db.alertdao;

import java.util.List;

import com.cloudlanes.db.dao.GenericDao;
import com.cloudlanes.db.entities.Alert;


public interface AlertDao extends GenericDao<Alert, Integer>{

	/**
	 * method add Alert
	 *
	 * @param alert
	 * @return boolean
	 */
	public boolean addAlert(Alert a);

	/**
	 * method for remove alert
	 *
	 * @param alert
	 * @return boolean
	 */
	public boolean removeAlert(Alert a);

	/**
	 * method for update alert
	 *
	 * @param alert
	 * @return boolean
	 */
	public boolean updateAlert(Alert a);

	/**
	 * List alerts
	 *
	 * @param key
	 * @return list
	 */
	public List<Alert> listAlert();
}
