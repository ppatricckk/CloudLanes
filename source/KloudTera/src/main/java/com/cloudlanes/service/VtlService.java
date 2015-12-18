package com.cloudlanes.service;

import java.util.List;

import com.cloudlanes.db.entities.DriveModel;
import com.cloudlanes.db.entities.DriveVendor;
import com.cloudlanes.db.entities.MediaType;
import com.cloudlanes.db.entities.Vtl;
import com.cloudlanes.db.entities.VtlBrand;
import com.cloudlanes.db.entities.VtlModel;
import com.cloudlanes.rest.inobject.VtlDto;
import com.cloudlanes.rest.outobject.VtlStatusDto;

public interface VtlService {
	
	public VtlStatusDto createCtl(VtlDto dto);
	
	public List<VtlBrand> getVtlBrands();
	
	public List<VtlModel> getSupportedVtlModels(int vtlBrandId);
	
	public List<DriveVendor> getSupportedDriveVendors(int vtlBrandId);
	
	public List<DriveModel> getSupportedDriveModels(int vtlBrandId);
	
	public List<MediaType> getSupportedMediaTypes(int vtlBrandId);
	
	public List<Vtl> getVtlList();
	
	public Vtl getVtlByName(String libraryName);
}
