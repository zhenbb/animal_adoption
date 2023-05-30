package com.example.animal_adoption.service.ifs;

import com.example.animal_adoption.vo.MemberUpdateRequest;

import java.time.LocalDate;

import com.example.animal_adoption.entity.Member;
import com.example.animal_adoption.vo.MemberAccountRequest;
import com.example.animal_adoption.vo.MemberResponse;
import com.example.animal_adoption.vo.MemberSignUpRequest;

public interface MemberService {
	
	//會員註冊
	public MemberResponse signUp(Member member);
	
	//帳號生效
	public MemberResponse activeAccount(String memberId, String pwd);
	
	//會員登入
	public MemberResponse logIn(String memberId, String pwd);
	
	//修改會員密碼
	public MemberResponse updatePwd(String memberId, String pwd);
	
	//修改會員姓名
	public MemberResponse updateMemberName(String memberId, String memberName);
	
	//修改會員手機
	public MemberResponse updatePhone(String memberId, String phone);

	//修改會員生日
	public MemberResponse updateBirthday(String memberId, LocalDate birth);

	
	
}
