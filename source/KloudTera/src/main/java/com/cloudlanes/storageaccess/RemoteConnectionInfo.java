package com.cloudlanes.storageaccess;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RemoteConnectionInfo {

	 @Value("${remote.jsch.username}")
	private String userName;
	 @Value("${remote.jsch.password}")
	private String password;
	 @Value("${remote.jsch.host}")
	private String host;
	 @Value("${remote.jsch.port}")
	private int port;

	public RemoteConnectionInfo() {

	}

	/*public RemoteConnectionInfo(String userName, String password, String host, int port) {
	super();
	this.userName = userName;
	this.password = password;
	this.host = host;
	this.port = port;
    }*/
	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "RemoteConnectionInfo [userName=" + userName + ", host=" + host + ", port=" + port + "]";
	}
}
