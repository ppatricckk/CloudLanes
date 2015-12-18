package com.cloudlanes.db.drivemodeldao;

import org.springframework.stereotype.Repository;

import com.cloudlanes.db.dao.GenericDaoImpl;
import com.cloudlanes.db.entities.DriveModel;

@Repository
public class DriveModelDaoImpl  extends GenericDaoImpl<DriveModel, Integer> implements DriveModelDao {
	
	public DriveModelDaoImpl() {
			daoType = DriveModel.class;
	}
}
