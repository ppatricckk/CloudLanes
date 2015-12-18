package com.cloudlanes.rest.outobject;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Status implements Serializable {
	public boolean status;
	public String message;
	
	public Status() {
		super();
	}

	public Status(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	

}
