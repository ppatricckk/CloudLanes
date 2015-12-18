package com.cloudlanes.db.vtlmodeldao;

import org.springframework.stereotype.Repository;

import com.cloudlanes.db.dao.GenericDaoImpl;
import com.cloudlanes.db.entities.VtlModel;

@Repository
public class VtlModelDaoImpl  extends GenericDaoImpl<VtlModel, Integer> implements VtlModelDao {
	
	public VtlModelDaoImpl() {
			daoType = VtlModel.class;
	}
}
