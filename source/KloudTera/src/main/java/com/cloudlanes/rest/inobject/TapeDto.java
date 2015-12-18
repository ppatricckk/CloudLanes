package com.cloudlanes.rest.inobject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TapeDto {
	
	@XmlElement
	private String libraryName;
	
	@XmlElement
	private MediaTypeDto mediaType;
	
	@XmlElement
	private int noOfTapes;
	
	@XmlElement
	private int mediaCapacityMB;
	
	@XmlElement
	private String barcode;
	
	@XmlElement
	private String status;
	
	@XmlElement
	private boolean loadedInToDrive;
	
	@XmlElement
	private int slotId;
	
	@XmlElement
	private int driveId;

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public MediaTypeDto getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaTypeDto mediaType) {
		this.mediaType = mediaType;
	}

	public int getNoOfTapes() {
		return noOfTapes;
	}

	public void setNoOfTapes(int noOfTapes) {
		this.noOfTapes = noOfTapes;
	}

	public int getMediaCapacityMB() {
		return mediaCapacityMB;
	}

	public void setMediaCapacityMB(int mediaCapacityMB) {
		this.mediaCapacityMB = mediaCapacityMB;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isLoadedInToDrive() {
		return loadedInToDrive;
	}

	public void setLoadedInToDrive(boolean loadedInToDrive) {
		this.loadedInToDrive = loadedInToDrive;
	}

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public int getDriveId() {
		return driveId;
	}

	public void setDriveId(int driveId) {
		this.driveId = driveId;
	}
}
