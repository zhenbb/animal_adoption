package com.example.animal_adoption.vo;

public class MemberResponse {
	
	private String message;

	public MemberResponse() {
		
	}

	public MemberResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
