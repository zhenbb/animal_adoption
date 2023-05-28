package com.example.animal_adoption.vo;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

public class MemberUpdateRequest {
	
	private String pwd;
	
	private String memberName;
	
	private String phone;
	
	private LocalDate birth;
	
	private HttpSession httpSession;

	public MemberUpdateRequest() {
		
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public HttpSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	
}