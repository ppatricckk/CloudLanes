package com.cloudlanes.db.drivevendordao;

import org.springframework.stereotype.Repository;

import com.cloudlanes.db.dao.GenericDaoImpl;
import com.cloudlanes.db.entities.DriveVendor;

@Repository
public class DriveVendorDaoImpl  extends GenericDaoImpl<DriveVendor, Integer> implements DriveVendorDao {
	
	public DriveVendorDaoImpl() {
			daoType = DriveVendor.class;
	}
}
