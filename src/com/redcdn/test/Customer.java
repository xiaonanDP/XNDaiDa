package com.redcdn.test;

public class Customer {

	private String count;
	private int clientType;
	private String reginCode;
	private String contactor;
	private String loginStr;
	
	public String getContactor() {
		return contactor;
	}
	public void setContactor(String contactor) {
		this.contactor = contactor;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public int getClientType() {
		return clientType;
	}
	public void setClientType(int clientType) {
		this.clientType = clientType;
	}
	public String getReginCode() {
		return reginCode;
	}
	public void setReginCode(String reginCode) {
		this.reginCode = reginCode;
	}
	public String getLoginStr() {
		return loginStr;
	}
	public void setLoginString(String loginString) {
		this.loginStr = loginString;
	}
	public Customer(String count, int clientType, String reginCode,String contactor,
			String loginString) {
		super();
		this.count = count;
		this.clientType = clientType;
		this.reginCode = reginCode;
		this.contactor = contactor;
		this.loginStr = loginString;
	}
	
	public String print(){
		return "账号:"+count+"\t到期时间:"+clientType+"\t区:"+reginCode+"\t联系方式:"+contactor+"\n";
	}
	
}
