package com.example.animal_adoption.vo;

import javax.servlet.http.HttpSession;

public class MemberAccountRequest {

	private String memberId;
	
	private String pwd;
	
	private HttpSession httpSession;

	public MemberAccountRequest() {
		
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public HttpSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	
}
