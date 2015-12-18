package com.cloudlanes.rest.outobject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TapeStatusDto {

	@XmlElement
	private boolean hasError;
	
	@XmlElement
	private String statusMessage;
	
	@XmlElement
	private String libraryName;
	
	@XmlElement
	private String mediaType;
	
	@XmlElement
	private int noOfTapes;
	
	@XmlElement
	private int capacityMB;
	
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

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public int getNoOfTapes() {
		return noOfTapes;
	}

	public void setNoOfTapes(int noOfTapes) {
		this.noOfTapes = noOfTapes;
	}

	public int getCapacityMB() {
		return capacityMB;
	}

	public void setCapacityMB(int capacityMB) {
		this.capacityMB = capacityMB;
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
