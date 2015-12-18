package com.cloudlanes.rest.inobject;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VtlDto {
	
	@XmlElement
	private String libraryName;
	
	@XmlElement
	private VtlModelDto vtlModel;
	
	@XmlElement
	private VtlBrandDto vtlBrand;
	
	@XmlElement
	private DriveVendorDto driveVendor;
	
	@XmlElement
	private DriveModelDto driveModel;
	
	@XmlElement
	private MediaTypeDto mediaType;
	
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
	
	@XmlElement
	private boolean compressionEnabled;
	
	@XmlElement
	private int compressionFactor;
	
	@XmlElement
	private int mediaCapacityMB;
	
	@XmlElement
	private int vaultId;

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
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

	public boolean isCompressionEnabled() {
		return compressionEnabled;
	}

	public void setCompressionEnabled(boolean compressionEnabled) {
		this.compressionEnabled = compressionEnabled;
	}

	public int getCompressionFactor() {
		return compressionFactor;
	}

	public void setCompressionFactor(int compressionFactor) {
		this.compressionFactor = compressionFactor;
	}

	public int getMediaCapacityMB() {
		return mediaCapacityMB;
	}

	public void setMediaCapacityMB(int mediaCapacityMB) {
		this.mediaCapacityMB = mediaCapacityMB;
	}

	public int getVaultId() {
		return vaultId;
	}

	public void setVaultId(int vaultId) {
		this.vaultId = vaultId;
	}
	
	public VtlModelDto getVtlModel() {
		return vtlModel;
	}

	public void setVtlModel(VtlModelDto vtlModel) {
		this.vtlModel = vtlModel;
	}

	public VtlBrandDto getVtlBrand() {
		return vtlBrand;
	}

	public void setVtlBrand(VtlBrandDto vtlBrand) {
		this.vtlBrand = vtlBrand;
	}

	public DriveVendorDto getDriveVendor() {
		return driveVendor;
	}

	public void setDriveVendor(DriveVendorDto driveVendor) {
		this.driveVendor = driveVendor;
	}

	public DriveModelDto getDriveModel() {
		return driveModel;
	}

	public void setDriveModel(DriveModelDto driveModel) {
		this.driveModel = driveModel;
	}

	public MediaTypeDto getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaTypeDto mediaType) {
		this.mediaType = mediaType;
	}
}
