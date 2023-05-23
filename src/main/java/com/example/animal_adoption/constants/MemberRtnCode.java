package com.example.animal_adoption.constants;


public enum MemberRtnCode {
	
	SUCCESSFUL("200", "成功");
	
	private String code;

	private String message;

	private MemberRtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

