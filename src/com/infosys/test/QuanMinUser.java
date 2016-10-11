package com.infosys.test;

public class QuanMinUser {

	private String regionNumber;
	private String sessionId;
	private int endDate;
	private String qqNumber;
	
	
	public int getEndDate() {
		return endDate;
	}
	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}
	public String getQqNumber() {
		return qqNumber;
	}
	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}
	public String getRegionNumber() {
		return regionNumber;
	}
	public void setRegionNumber(String regionNumber) {
		this.regionNumber = regionNumber;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public QuanMinUser(String regionNumber, String sessionId) {
		super();
		this.regionNumber = regionNumber;
		this.sessionId = sessionId;
	}
	public QuanMinUser(String regionNumber, String sessionId, int endDate,
			String qqNumber) {
		super();
		this.regionNumber = regionNumber;
		this.sessionId = sessionId;
		this.endDate = endDate;
		this.qqNumber = qqNumber;
	}
	
}
