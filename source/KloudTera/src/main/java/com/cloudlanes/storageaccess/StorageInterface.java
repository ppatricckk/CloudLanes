package com.cloudlanes.storageaccess;

import java.util.List;

import com.cloudlanes.db.entities.DriveModel;
import com.cloudlanes.db.entities.DriveVendor;
import com.cloudlanes.db.entities.MediaType;
import com.cloudlanes.db.entities.Tape;
import com.cloudlanes.db.entities.Vtl;
import com.cloudlanes.db.entities.VtlBrand;
import com.cloudlanes.db.entities.VtlModel;
import com.cloudlanes.rest.inobject.TapeDto;
import com.cloudlanes.rest.inobject.VtlDto;


public interface StorageInterface {
	public Vtl createVtl(VtlDto vtlDto, VtlBrand brand, VtlModel vtlModel, DriveVendor driveVendor, DriveModel driveModel, MediaType mediaType);
	public void deleteVtl();
	public void listVtl();
	public List<Tape> createTapes(TapeDto tapeDto, Vtl vtl, MediaType mediaType);
	public void listTape();
	//TODO: remove unnecessary methods
	public void deleteTape();
	public void loadtape();
	public void unloadTape();
	public void barcodeTape();
}
