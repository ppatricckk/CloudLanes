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

import com.cloudlanes.db.entities.Drive;
import com.cloudlanes.db.entities.Slot;
import com.cloudlanes.db.entities.Tape;
import com.cloudlanes.db.entities.Vtl;
import com.cloudlanes.db.entities.VtlBrand;
import com.cloudlanes.rest.inobject.MediaTypeDto;
import com.cloudlanes.rest.inobject.TapeDto;
import com.cloudlanes.rest.outobject.TapeStatusDto;
import com.cloudlanes.service.TapeService;
import com.cloudlanes.service.VtlService;

@RestController
@RequestMapping(value = "/tapeservice")
public class TapeController {
	
	@Autowired
	private VtlService vtlService;
	
	@Autowired
	private TapeService tapeService;
	
	
	@RequestMapping(value = "/mediaTypes", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MediaTypeDto> getSupportedMediaTypes(@RequestParam("libraryName") String libraryName) {
		List<MediaTypeDto> dtoList = new ArrayList<>();
		
		Vtl library = vtlService.getVtlByName(libraryName);
		if(library != null) {
			VtlBrand vtlBrand = library.getVtlBrand();
			List<com.cloudlanes.db.entities.MediaType> mediaTypeList = vtlService.getSupportedMediaTypes(vtlBrand.getBrandId());
			for(com.cloudlanes.db.entities.MediaType type : mediaTypeList) {
				MediaTypeDto dto = new MediaTypeDto();
				dto.setMediaTypeId(type.getTypeId());
				dto.setMediaTypeName(type.getTypeName());
				dto.setCapacityMB(type.getRawCapacityMB());
			}
		}
		return dtoList;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public TapeStatusDto createTape(@RequestBody TapeDto tapeDto) {
		return tapeService.createTape(tapeDto);
	}

	@RequestMapping(value = "/tapeList", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<TapeDto> getTapeList(@RequestParam("libraryName") String libraryName) {
		List<TapeDto> tapeListDto = new ArrayList<>();
		
		Vtl library = vtlService.getVtlByName(libraryName);
		if(library != null) {
			int libraryId = library.getVtlId();
			List<Tape> tapeList = tapeService.getTapesForLibrary(libraryId);
			if(tapeList != null) {
				for(Tape tape : tapeList) {
					TapeDto dto = new TapeDto();
					
					dto.setBarcode(tape.getBarcode());
					//Currently UI does not allow capacity edit
					//TODO: add to properties file
					dto.setMediaCapacityMB(tape.getSizeMB());
					Drive drive = tape.getDrive();
					if(drive != null) {
						dto.setDriveId(drive.getDriveId());
					}
					dto.setLibraryName(libraryName);
					dto.setLoadedInToDrive(tape.getIsLoaded() == 1? true:false);
					MediaTypeDto mediaTypeDto = new MediaTypeDto();
					mediaTypeDto.setMediaTypeId(tape.getMediaType().getTypeId());
					mediaTypeDto.setMediaTypeName(tape.getMediaType().getTypeName());
					mediaTypeDto.setCapacityMB(tape.getMediaType().getRawCapacityMB());
					dto.setMediaType(mediaTypeDto);
					
					Slot slot = tape.getSlot();
					if(slot != null) { 						
						dto.setSlotId(slot.getSlotId());
					}
					dto.setStatus(tape.getStatus());
					
					tapeListDto.add(dto);
				}
			}
		}
		return tapeListDto;
	}
}
