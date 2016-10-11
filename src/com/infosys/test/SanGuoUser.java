package com.infosys.test;

public class SanGuoUser {

	private String account;
	private int serviceDate;
	private String reginCode;
	private String contactor;
	private String loginStrA;
	private String loginStrB;
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(int serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getReginCode() {
		return reginCode;
	}

	public void setReginCode(String reginCode) {
		this.reginCode = reginCode;
	}

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getLoginStrA() {
		return loginStrA;
	}

	public void setLoginStrA(String loginStrA) {
		this.loginStrA = loginStrA;
	}

	public String getLoginStrB() {
		return loginStrB;
	}

	public void setLoginStrB(String loginStrB) {
		this.loginStrB = loginStrB;
	}

	public SanGuoUser(String account, int serviceDate, String reginCode,
			String contactor, String loginStrA, String loginStrB) {
		super();
		this.account = account;
		this.serviceDate = serviceDate;
		this.reginCode = reginCode;
		this.contactor = contactor;
		this.loginStrA = loginStrA;
		this.loginStrB = loginStrB;
	}

	public String print(){
		return "账号:"+account+"\t到期时间:"+serviceDate+"\t区:"+reginCode+"\t联系方式:"+contactor+"\n";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
