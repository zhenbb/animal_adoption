package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Member;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberResponse {
	
	@JsonProperty("session_id")
	private String sessionId;
	
	@JsonProperty("verify_code")
	private int verifyCode;
	
	private Member member;
	
	private String message;

	public MemberResponse() {
		
	}

	public MemberResponse(String sessionId, int verifyCode, String message) {
		this.sessionId = sessionId;
		this.verifyCode = verifyCode;
		this.message = message;
	}

	public MemberResponse(Member member, String message) {
		this.member = member;
		this.message = message;
	}

	public MemberResponse(String message) {
		this.message = message;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
