package com.cloudlanes.db.vtlbranddao;

import org.springframework.stereotype.Repository;

import com.cloudlanes.db.dao.GenericDaoImpl;
import com.cloudlanes.db.entities.VtlBrand;

@Repository
public class VtlBrandDaoImpl  extends GenericDaoImpl<VtlBrand, Integer> implements VtlBrandDao {
	
	public VtlBrandDaoImpl() {
			daoType = VtlBrand.class;
	}
}
