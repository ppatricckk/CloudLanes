package com.cloudlanes.rest.inobject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DriveModelDto {
	@XmlElement
	private int driveModelId;
	@XmlElement
	private String driveModelName;
	public int getDriveModelId() {
		return driveModelId;
	}
	public void setDriveModelId(int driveModelId) {
		this.driveModelId = driveModelId;
	}
	public String getDriveModelName() {
		return driveModelName;
	}
	public void setDriveModelName(String driveModelName) {
		this.driveModelName = driveModelName;
	}
}
