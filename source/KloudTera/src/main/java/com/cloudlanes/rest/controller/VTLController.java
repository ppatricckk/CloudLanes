package com.cloudlanes.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudlanes.db.entities.DriveModel;
import com.cloudlanes.db.entities.DriveVendor;
import com.cloudlanes.db.entities.Vtl;
import com.cloudlanes.db.entities.VtlBrand;
import com.cloudlanes.db.entities.VtlModel;
import com.cloudlanes.rest.inobject.DriveModelDto;
import com.cloudlanes.rest.inobject.DriveVendorDto;
import com.cloudlanes.rest.inobject.MediaTypeDto;
import com.cloudlanes.rest.inobject.VtlBrandDto;
import com.cloudlanes.rest.inobject.VtlDto;
import com.cloudlanes.rest.inobject.VtlModelDto;
import com.cloudlanes.rest.outobject.VtlStatusDto;
import com.cloudlanes.service.VtlService;

@RestController
@RequestMapping(value = "/vtlservice")
public class VTLController {
	
	@Autowired
	private VtlService vtlservice;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public VtlStatusDto addVTL(@RequestBody VtlDto vtlobj) throws Exception {
		return vtlservice.createCtl(vtlobj);
	}
	
	@RequestMapping(value = "/vtlBrands", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<VtlBrandDto> getVtlBrands() {
		List<VtlBrandDto> dtoList = new ArrayList<>();
		
		List<VtlBrand> brandList = vtlservice.getVtlBrands();
		for(VtlBrand brand : brandList) {
			VtlBrandDto dto = new VtlBrandDto();
			dto.setBrandId(brand.getBrandId());
			dto.setBrandName(brand.getBrandName());
		}
		return dtoList;
	}
 
	@RequestMapping(value = "/vtlModels", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<VtlModelDto> getSupportedVtlModels(@RequestParam("brandId") int vtlBrandId) {
		List<VtlModelDto> dtoList = new ArrayList<>();
		
		List<VtlModel> modelList = vtlservice.getSupportedVtlModels(vtlBrandId);
		for(VtlModel model : modelList) {
			VtlModelDto dto = new VtlModelDto();
			dto.setVtlModelId(model.getModelId());
			dto.setVtlModelName(model.getModelName());
		}
		return dtoList;		 
	}

	@RequestMapping(value = "/driveVendors", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<DriveVendorDto> getSupportedDriveVendors(@RequestParam("brandId")int vtlBrandId) {
		List<DriveVendorDto> dtoList = new ArrayList<>();
		
		List<DriveVendor> vendorList = vtlservice.getSupportedDriveVendors(vtlBrandId);
		for(DriveVendor vendor : vendorList) {
			DriveVendorDto dto = new DriveVendorDto();
			dto.setDriveVendorId(vendor.getVendorId());
			dto.setDriveVendorName(vendor.getVendorName());
		}
		return dtoList;	
	}

	@RequestMapping(value = "/driveModels", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<DriveModelDto> getSupportedDriveModels(@RequestParam("brandId")int vtlBrandId) {
		List<DriveModelDto> dtoList = new ArrayList<>();
		
		List<DriveModel> modelList = vtlservice.getSupportedDriveModels(vtlBrandId);
		for(DriveModel model : modelList) {
			DriveModelDto dto = new DriveModelDto();
			dto.setDriveModelId(model.getModelId());
			dto.setDriveModelName(model.getModelName());
		}
		return dtoList;
	}

	@RequestMapping(value = "/mediaTypes", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MediaTypeDto> getSupportedMediaTypes(@RequestParam("brandId")int vtlBrandId) {
		List<MediaTypeDto> dtoList = new ArrayList<>();
		
		List<com.cloudlanes.db.entities.MediaType> typeList = vtlservice.getSupportedMediaTypes(vtlBrandId);
		for(com.cloudlanes.db.entities.MediaType type : typeList) {
			MediaTypeDto dto = new MediaTypeDto();
			dto.setMediaTypeId(type.getTypeId());
			dto.setMediaTypeName(type.getTypeName());
			dto.setCapacityMB(type.getRawCapacityMB());
		}
		return dtoList;
	}

	@RequestMapping(value = "/vtlList", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<VtlDto> getVtlList() {
		List<VtlDto> vtlDtoList = new ArrayList<>();
		
		List<Vtl> vtlList = vtlservice.getVtlList();
		for(Vtl vtl : vtlList) {
			VtlDto vtlDto = new VtlDto();
			vtlDto.setCompressionEnabled(vtl.getCompressionEnabled() == 1 ? true : false);
			vtlDto.setCompressionFactor(vtl.getCompressionFactor());
			VtlBrandDto brandDto = new VtlBrandDto();
			brandDto.setBrandId(vtl.getVtlBrand().getBrandId());
			brandDto.setBrandName(vtl.getVtlBrand().getBrandName());
			vtlDto.setVtlBrand(brandDto);
			
			VtlModelDto modelDto = new VtlModelDto();
			modelDto.setVtlModelId(vtl.getVtlModel().getModelId());
			modelDto.setVtlModelName(vtl.getVtlModel().getModelName());
			vtlDto.setVtlModel(modelDto);
			
			vtlDto.setLibraryName(vtl.getVtlName());
			vtlDto.setNoOfDrives(vtl.getDriveCount());
			vtlDto.setNoOfEmptySlots(vtl.getEmptySlotCount());
			vtlDto.setNoOfMaps(vtl.getIePortCount());
			vtlDto.setNoOfPickers(vtl.getPickerCount());
			vtlDto.setNoOfSlots(vtl.getSlotCount());
			vtlDto.setMediaCapacityMB(500);
			//vtlDto.setVaultId(vtl.getVault().getVaultId());
			vtlDtoList.add(vtlDto);
		}
		return vtlDtoList;
		
	}
}
