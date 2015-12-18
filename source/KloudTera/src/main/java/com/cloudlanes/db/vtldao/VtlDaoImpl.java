package com.cloudlanes.db.vtldao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cloudlanes.db.dao.GenericDaoImpl;
import com.cloudlanes.db.entities.DriveModel;
import com.cloudlanes.db.entities.DriveVendor;
import com.cloudlanes.db.entities.MediaType;
import com.cloudlanes.db.entities.Vtl;
import com.cloudlanes.db.entities.VtlBrand;
import com.cloudlanes.db.entities.VtlModel;

@Repository
public class VtlDaoImpl extends GenericDaoImpl<Vtl, Integer> implements VtlDao {

	public VtlDaoImpl() {
		daoType = Vtl.class;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<VtlBrand> getVtlBrands() {
		String qry="FROM VtlBrand";
		Query brandQuery = currentSession()
				.createQuery(qry);
		return (List<VtlBrand>) brandQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VtlModel> getSupportedVtlModels(int vtlBrandId) {
		Query q = currentSession().createQuery("FROM VtlModel a WHERE a.vtlBrand.brandId = :id");
		q.setParameter("id", vtlBrandId);
		List<VtlModel> models = (List<VtlModel>) q.list();
		return models;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriveVendor> getSupportedDriveVendors(int vtlBrandId) {
		Query q = currentSession().createQuery("FROM DriveVendor a WHERE a.vtlBrand.brandId = :id");
		q.setParameter("id", vtlBrandId);
		return (List<DriveVendor>) q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriveModel> getSupportedDriveModels(int vtlBrandId) {
		Query q = currentSession().createQuery("FROM DriveModel a WHERE a.vtlBrand.brandId = :id");
		q.setParameter("id", vtlBrandId);
		return (List<DriveModel>) q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MediaType> getSupportedMediaTypes(int vtlBrandId) {
		Query q = currentSession().createQuery("FROM MediaType a WHERE a.vtlBrand.brandId = :id");
		q.setParameter("id", vtlBrandId);
		return (List<MediaType>) q.list();
	}
}
