package com.example.animal_adoption.constants;


public enum MemberRtnCode {
	
	LOG_IN_SUCCESS("200", "登入成功"),
	LOG_OUT_SUCCESS("200", "登出成功"),
	SIGN_UP_SUCCESS("200", "註冊成功"),
	ACTTIVE_MEMBER_SUCCESS("200", "帳號生效成功"),
	UPDATE_MEMBER_INFO_SUCCESS("200", "更新會員資訊成功"),
	MEMBER_IS_PRESENT("400", "已註冊會員"),
	MEMBER_NOT_PRESENT("400", "尚未註冊會員"),
	MEMBER_NOT_PRESENT_OR_PWD_ERROR_OR_NOT_ACTIVE("400", "尚未註冊會員或密碼錯誤或尚未生效會員"),
	MEMBER_ALREADY_ACTIVE("400", "會員已經生效"),
	MEMBER_NOT_ACTIVE("400", "會員尚未生效"),
	SAME_PWD("400", "密碼不可與原本相同"),
	SAME_MEMBER_NAME("400", "姓名不可與原本相同"),
	SAME_PHONE("400", "手機不可與原本相同"),
	SAME_BIRTHDAY("400", "生日不可與原本相同"),
	NOT_LOG_IN("400", "未登入會員"),
	
	
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

