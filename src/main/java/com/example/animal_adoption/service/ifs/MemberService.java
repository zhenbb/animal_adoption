package com.example.animal_adoption.service.ifs;

import com.example.animal_adoption.vo.MemberResponse;
import com.example.animal_adoption.vo.MemberRequest;

public interface MemberService {

	//會員註冊
	public MemberResponse signUp(MemberRequest signUpRequest);

	//帳號生效
	public MemberResponse activeAccount(MemberRequest accountRequest);

	//會員登入
	public MemberResponse logIn(MemberRequest accountRequest);

	//會員登入驗證
	public MemberResponse logInVerify(MemberRequest accountRequest);

	//修改會員密碼
	public MemberResponse updatePwd(MemberRequest updateRequest);

	//修改會員姓名
	public MemberResponse updateMemberName(MemberRequest updateRequest);

	//修改會員手機
	public MemberResponse updatePhone(MemberRequest updateRequest);

	//修改會員生日
	public MemberResponse updateBirthday(MemberRequest updateRequest);



}