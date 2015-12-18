package com.cloudlanes.storageaccess;

public class ScsiDeviceInfo {
	private String deviceType;
	private String deviceFilePath;
	
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceFilePath() {
		return deviceFilePath;
	}
	public void setDeviceFilePath(String deviceFilePath) {
		this.deviceFilePath = deviceFilePath;
	}
	@Override
	public String toString() {
		return "ScsiDeviceInfo [deviceType=" + deviceType + ", deviceFilePath="
				+ deviceFilePath + "]";
	}	
}
