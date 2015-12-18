package com.cloudlanes.db.alertdao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cloudlanes.db.dao.GenericDaoImpl;
import com.cloudlanes.db.entities.AlertRecommendation;

@Repository
public class AlertRecommendationDAOImpl extends
		GenericDaoImpl<AlertRecommendation, Integer> implements
		AlertRecommendationDAO {

	final static Logger logger = Logger
			.getLogger(AlertRecommendationDAOImpl.class);
	private static final String CLASS_NAME = AlertRecommendationDAOImpl.class
			.getSimpleName();

	@Override
	public boolean addRecommendation(AlertRecommendation recommendation) {
		try {
			currentSession().saveOrUpdate(recommendation);
			logger.debug("Row inserted in AlertRecommendation table ID : "
					+ recommendation.getId());
			return true;
		} catch (Exception e) {
			logger.error("corresponding object not inserted in database");
			return false;
		}
	}

	@Override
	public boolean removeRecommendation(AlertRecommendation recommendation) {
		try {
			currentSession().delete(recommendation);
			logger.debug("Row deleted from AlertRecommendation table ID :"
					+ recommendation.getId());
			return true;
		} catch (Exception e) {
			logger.error("corresponding AlertRecommendation object not deleted from database ID : "
					+ recommendation.getId());
			return false;
		}
	}

	@Override
	public boolean updateRecommendation(AlertRecommendation recommendation) {
		try {
			currentSession().saveOrUpdate(recommendation);
			logger.debug("Row updated in AlertRecommendation table ID : "
					+ recommendation.getId());
			return true;
		} catch (Exception e) {
			logger.error("corresponding AlertRecommendation object is updated in database");
			return false;
		}
	}

	@Override
	public AlertRecommendation getRecommendation(Double code) {
		String qry = "from AlertRecommendation a where a.code = ?";
		Query recommendationQuery = currentSession().createQuery(qry);
		recommendationQuery.setParameter(0, code);
		if (recommendationQuery.list().size() > 0) {
			return (AlertRecommendation) recommendationQuery.list().get(0);
		} else {
			AlertRecommendation recomm = new AlertRecommendation();
			recomm.setId(0);
			recomm.setCode(code);
			recomm.setRecommendation("No recommendation data available for the alert.");
			return recomm;
		}

	}

}
