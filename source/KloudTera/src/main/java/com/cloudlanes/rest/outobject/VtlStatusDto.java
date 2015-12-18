package com.cloudlanes.rest.outobject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VtlStatusDto {
	@XmlElement
	private boolean hasError;
	
	@XmlElement
	private String statusMessage;
	
	@XmlElement
	private String libraryName;
	
	@XmlElement
	private String libraryBrand;
	
	@XmlElement
	private String libraryModel;
	
	@XmlElement
	private int noOfSlots;
	
	@XmlElement
	private int noOfMaps;
	
	@XmlElement
	private int noOfPickers;
	
	@XmlElement
	private int noOfEmptySlots;
	
	@XmlElement
	private int noOfDrives;

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

	public String getLibraryBrand() {
		return libraryBrand;
	}

	public void setLibraryBrand(String libraryBrand) {
		this.libraryBrand = libraryBrand;
	}

	public String getLibraryModel() {
		return libraryModel;
	}

	public void setLibraryModel(String libraryModel) {
		this.libraryModel = libraryModel;
	}

	public int getNoOfSlots() {
		return noOfSlots;
	}

	public void setNoOfSlots(int noOfSlots) {
		this.noOfSlots = noOfSlots;
	}

	public int getNoOfMaps() {
		return noOfMaps;
	}

	public void setNoOfMaps(int noOfMaps) {
		this.noOfMaps = noOfMaps;
	}

	public int getNoOfPickers() {
		return noOfPickers;
	}

	public void setNoOfPickers(int noOfPickers) {
		this.noOfPickers = noOfPickers;
	}

	public int getNoOfEmptySlots() {
		return noOfEmptySlots;
	}

	public void setNoOfEmptySlots(int noOfEmptySlots) {
		this.noOfEmptySlots = noOfEmptySlots;
	}

	public int getNoOfDrives() {
		return noOfDrives;
	}

	public void setNoOfDrives(int noOfDrives) {
		this.noOfDrives = noOfDrives;
	}
}
