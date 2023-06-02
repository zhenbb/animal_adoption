package com.example.animal_adoption.vo;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberAccountRequest {

	@JsonProperty("member_id")
	private String memberId;
	
	@JsonProperty("password")
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
