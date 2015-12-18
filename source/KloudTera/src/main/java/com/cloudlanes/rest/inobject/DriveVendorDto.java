package com.cloudlanes.rest.inobject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DriveVendorDto {
	@XmlElement
	private int driveVendorId;
	@XmlElement
	private String driveVendorName;
	public int getDriveVendorId() {
		return driveVendorId;
	}
	public void setDriveVendorId(int driveVendorId) {
		this.driveVendorId = driveVendorId;
	}
	public String getDriveVendorName() {
		return driveVendorName;
	}
	public void setDriveVendorName(String driveVendorName) {
		this.driveVendorName = driveVendorName;
	}
	
}
