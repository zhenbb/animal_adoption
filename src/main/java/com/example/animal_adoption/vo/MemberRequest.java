package com.example.animal_adoption.vo;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import com.example.animal_adoption.entity.Member;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberRequest {
	
	@JsonProperty("member_id")
	private String memberId;
	
	@JsonProperty("password")
	private String pwd;
	
	@JsonProperty("member_name")
	private String memberName;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("birthday")
	private String birth;
	
	@JsonProperty("verify_code")
	private int verifyCode;
	
	private HttpSession httpSession;

	public MemberRequest() {
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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}

	public HttpSession getHttpSession() {
		return httpSession;
	}
	
}
