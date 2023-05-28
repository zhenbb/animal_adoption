package com.example.animal_adoption.constants;


public enum MemberRtnCode {
	
	LOG_IN_SUCCESS("200", "登入成功"),
	LOG_OUT_SUCCESS("200", "登出成功"),
	SIGN_UP_SUCCESS("200", "註冊成功"),
	ACTTIVE_ACCOUNT_SUCCESS("200", "帳號生效成功"),
	UPDATE_MEMBER_INFO_SUCCESS("200", "更新會員資訊成功"),
	MEMBER_IS_PRESENT("400", "已註冊會員"),
	
	INCORRECT_INFO_ERROR("400", "資料不正確"),
	INPUT_EMPTY_VALUE_ERROR("400", "輸入值為空"),
	INPUT_NOT_ALLOWED_BLANK_ERROR("400", "輸入不得為空白"),
	;
	
	
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

