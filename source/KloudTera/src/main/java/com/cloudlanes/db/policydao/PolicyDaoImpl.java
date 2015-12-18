package com.cloudlanes.db.policydao;

import java.util.List;



import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloudlanes.db.dao.GenericDaoImpl;
import com.cloudlanes.db.entities.Policy;

@Repository
public class PolicyDaoImpl extends GenericDaoImpl<Policy, Integer> implements
PolicyDao {
	final static Logger logger = Logger.getLogger(PolicyDaoImpl.class);
	private static final String CLASS_NAME = PolicyDaoImpl.class
			.getSimpleName();

	public boolean addPolicy(Policy policyObj) {
		try {
			currentSession().saveOrUpdate(policyObj);
			logger.info("Row inserted in policy table");
			return true;
		} catch (Exception e) {
			logger.error("corresponding object not inserted in database");
			return false;
		}

	}

	public boolean removePolicy(Policy policyObj) {

		try {
			currentSession().delete(policyObj);
			
			logger.info("Row deleted from policy table");
			return true;
		} catch (Exception e) {
			logger.error("corresponding policy object not deleted from database");
			return false;
		}
	}

	public boolean updatePolicy(Policy policyObj) {
		try {
			currentSession().saveOrUpdate(policyObj);
			logger.info("Row updated in policy table");
			return true;
		} catch (Exception e) {
			logger.error("corresponding policy object is updated in database");
			return false;
		}

	}

	public List<Policy> listPolicy() {
		String hql = "from Policy p ";
		Query query = currentSession().createQuery(hql);

		List<Policy> policyList = (List<Policy>) query.list();

		return policyList;
	}

}
