package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Member;

public class MemberResponse {
	
	private Member member;
	
	private String message;

	public MemberResponse() {
		
	}

	public MemberResponse(Member member, String message) {
		this.member = member;
		this.message = message;
	}

	public MemberResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
