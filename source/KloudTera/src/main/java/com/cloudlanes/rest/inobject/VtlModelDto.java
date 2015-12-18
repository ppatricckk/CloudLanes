package com.cloudlanes.rest.inobject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VtlModelDto {
	@XmlElement
	private int vtlModelId;
	@XmlElement
	private String vtlModelName;
	
	public int getVtlModelId() {
		return vtlModelId;
	}
	public void setVtlModelId(int vtlModelId) {
		this.vtlModelId = vtlModelId;
	}
	public String getVtlModelName() {
		return vtlModelName;
	}
	public void setVtlModelName(String vtlModelName) {
		this.vtlModelName = vtlModelName;
	}
	
}
