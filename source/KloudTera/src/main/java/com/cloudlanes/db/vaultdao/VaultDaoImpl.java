package com.cloudlanes.db.vaultdao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cloudlanes.db.dao.GenericDaoImpl;
import com.cloudlanes.db.entities.Vault;

@Repository
public class VaultDaoImpl extends GenericDaoImpl<Vault, Integer> implements
VaultDao {

	final static Logger logger = Logger.getLogger(VaultDaoImpl.class);
	private static final String CLASS_NAME = VaultDaoImpl.class.getSimpleName();
	
	public VaultDaoImpl() {
		daoType = Vault.class;
	}

	@Override
	public boolean createVault(Vault vaultObj) {
		try {
			currentSession().save(vaultObj);
			logger.info("Row inserted in Vault table");
			return true;
		} catch (Exception e) {
			logger.error("corresponding object of vault not added in database");
			return false;
		}

	}

	@Override
	public boolean removeVault(Vault vaultObj) {
		try {
			currentSession().delete(vaultObj);

			logger.info("Row deleted from Vault table");
			return true;
		} catch (Exception e) {
			logger.error("corresponding Vault object not deleted from database");
			return false;
		}
	}

	@Override
	public boolean updateVault(Vault vaultObj) {	
		try {
			currentSession().saveOrUpdate(vaultObj);
			logger.info("Row updated in Vault table");
			return true;
		} catch (Exception e) {
			logger.error("corresponding Vault object is updated in database");
			return false;
		}
	}


	@Override
	public List<Vault> listVault() {
		String hql = "from Vault v ";
		Query query = currentSession().createQuery(hql);

		List<Vault> vaultList = (List<Vault>) query.list();

		return vaultList;
	}

}
