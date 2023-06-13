package com.example.animal_adoption.constants;

public enum SessionCode {

    MEMBER_ID("member_id"),
    MEMBER_PWD("member_pwd");
	
	
	private String code;

	private SessionCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}