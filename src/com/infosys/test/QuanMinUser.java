package com.infosys.test;

public class QuanMinUser {

	private String gameType;
	private String userType;
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
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public QuanMinUser(String regionNumber, String sessionId) {
		super();
		this.regionNumber = regionNumber;
		this.sessionId = sessionId;
	}
	public QuanMinUser(String gameType, String userType, String regionNumber,
			String sessionId, int endDate, String qqNumber) {
		super();
		this.gameType = gameType;
		this.userType = userType;
		this.regionNumber = regionNumber;
		this.sessionId = sessionId;
		this.endDate = endDate;
		this.qqNumber = qqNumber;
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
