package com.cloudlanes.db.alertdao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cloudlanes.db.dao.GenericDaoImpl;
import com.cloudlanes.db.entities.Alert;

@Repository
public class AlertDaoImpl extends GenericDaoImpl<Alert, Integer> implements
		AlertDao {

	final static Logger logger = Logger.getLogger(AlertDaoImpl.class);
	private static final String CLASS_NAME = AlertDaoImpl.class.getSimpleName();

	@Override
	public boolean addAlert(Alert alert) {
		try {
			currentSession().saveOrUpdate(alert);
			logger.info("Row inserted in alert table");
			return true;
		} catch (Exception e) {
			logger.error("corresponding object not inserted in database");
			return false;
		}
	}

	@Override
	public boolean removeAlert(Alert alert) {
		try {
			currentSession().delete(alert);

			logger.info("Row deleted from alert table");
			return true;
		} catch (Exception e) {
			logger.error("corresponding alert object not deleted from database");
			return false;
		}
	}

	@Override
	public boolean updateAlert(Alert alert) {
		try {
			currentSession().saveOrUpdate(alert);
			logger.info("Row updated in alert table");
			return true;
		} catch (Exception e) {
			logger.error("corresponding alert object is updated in database");
			return false;
		}
	}

	@Override
	public List<Alert> listAlert() {
		String hql = "from Alert a ";
		Query query = currentSession().createQuery(hql);

		List<Alert> alertLst = (List<Alert>) query.list();

		return alertLst;
	}

}
