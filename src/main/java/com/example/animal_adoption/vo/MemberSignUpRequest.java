package com.example.animal_adoption.vo;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import com.example.animal_adoption.entity.Member;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberSignUpRequest {
	
	@JsonProperty("member")
	private Member member;
	
	@JsonProperty("member_id")
	private String memberId;
	
	@JsonProperty("password")
	private String pwd;
	
	@JsonProperty("member_name")
	private String memberName;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("birthday")
	private LocalDate birth;
	
	private HttpSession httpSession;

	public MemberSignUpRequest() {
	}
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
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
