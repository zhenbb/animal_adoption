package com.example.animal_adoption.vo;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import com.example.animal_adoption.entity.Member;

public class MemberSignupRequest {
	
	private Member member;
	
	private String memberId;
	
	private String pwd;
	
	private String memberName;
	
	private String phone;
	
	private LocalDate birth;
	
	private HttpSession httpSession;

	public MemberSignupRequest() {
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
