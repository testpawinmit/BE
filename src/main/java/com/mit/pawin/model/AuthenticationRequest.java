package com.mit.pawin.model;

import javax.validation.constraints.NotNull;

public class AuthenticationRequest {

	@NotNull
	private String username;
	private String password;
	private String machine;
	private String appTypeCode;

	
	public AuthenticationRequest(String username, String password, String machine, String appTypeCode) {
		super();
		this.username = username;
		this.password = password;
		this.machine = machine;
		this.appTypeCode = appTypeCode;
	}
	
	public AuthenticationRequest()
	{
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getAppTypeCode() {
		return appTypeCode;
	}

	public void setAppTypeCode(String appTypeCode) {
		this.appTypeCode = appTypeCode;
	}
}
