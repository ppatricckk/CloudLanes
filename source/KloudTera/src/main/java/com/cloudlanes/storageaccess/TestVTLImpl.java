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

public class TestVTLImpl implements StorageInterface {

	@Override
	public Vtl createVtl(VtlDto vtlDto, VtlBrand brand, VtlModel vtlModel,
			DriveVendor driveVendor, DriveModel driveModel, MediaType mediaType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVtl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listVtl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tape> createTapes(TapeDto tapeDto, Vtl vtl, MediaType mediaType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void listTape() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTape() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadtape() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unloadTape() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void barcodeTape() {
		// TODO Auto-generated method stub
		
	}
}
