package com.cloudlanes.rest.inobject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MediaTypeDto {
	public int getMediaTypeId() {
		return mediaTypeId;
	}
	public void setMediaTypeId(int mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}
	public String getMediaTypeName() {
		return mediaTypeName;
	}
	public void setMediaTypeName(String mediaTypeName) {
		this.mediaTypeName = mediaTypeName;
	}
	@XmlElement
	private int mediaTypeId;
	@XmlElement
	private String mediaTypeName;
	@XmlElement
	private int capacityMB;
	public int getCapacityMB() {
		return capacityMB;
	}
	public void setCapacityMB(int capacityMB) {
		this.capacityMB = capacityMB;
	}
	
}
