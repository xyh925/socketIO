package com.xys.pojo;

public class ClientUser {
	private String UserID;
	private String Port;
	public ClientUser(String UserID,String Port){
		this.UserID=UserID;
		this.Port=Port;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getPort() {
		return Port;
	}
	public void setPort(String port) {
		Port = port;
	}
}
