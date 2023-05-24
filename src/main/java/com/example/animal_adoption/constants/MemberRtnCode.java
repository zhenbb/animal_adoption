package com.example.animal_adoption.constants;


public enum MemberRtnCode {
	
	SUCCESSFUL_LOG_IN("200", "登入成功"),
	SUCCESSFUL_LOG_OUT("200", "登出成功"),
	SUCCESSFUL_SIGN_UP("200", "註冊成功"),
	SUCCESSFUL_ACTTIVE_ACCOUNT("200", "帳號生效成功"),
	SUCCESSFUL_UPDATE_MEMBER_INFO("200", "修改會員資訊成功");
	
	
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

