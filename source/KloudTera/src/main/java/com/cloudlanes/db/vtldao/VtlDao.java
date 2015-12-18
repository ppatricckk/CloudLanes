package com.cloudlanes.db.vtldao;

import java.util.List;

import com.cloudlanes.db.dao.GenericDao;
import com.cloudlanes.db.entities.DriveModel;
import com.cloudlanes.db.entities.DriveVendor;
import com.cloudlanes.db.entities.MediaType;
import com.cloudlanes.db.entities.Vtl;
import com.cloudlanes.db.entities.VtlBrand;
import com.cloudlanes.db.entities.VtlModel;

public interface VtlDao extends GenericDao<Vtl, Integer> {
	
	public List<VtlBrand> getVtlBrands();
	
	public List<VtlModel> getSupportedVtlModels(int vtlBrandId);

	public List<DriveVendor> getSupportedDriveVendors(int vtlBrandId);

	public List<DriveModel> getSupportedDriveModels(int vtlBrandId);

	public List<MediaType> getSupportedMediaTypes(int vtlBrandId);
}
