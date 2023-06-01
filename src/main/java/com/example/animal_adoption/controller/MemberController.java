package com.example.animal_adoption.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.animal_adoption.constants.MemberRtnCode;
import com.example.animal_adoption.service.ifs.MemberService;
import com.example.animal_adoption.vo.MemberRequest;
import com.example.animal_adoption.vo.MemberResponse;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping(value = "sign_up")
	public MemberResponse signUp(@RequestBody MemberRequest signUpRequest) {
		return memberService.signUp(signUpRequest);
	}
	
	@PostMapping(value = "active_account")
	public MemberResponse activeAccount(@RequestBody MemberRequest accountRequest) {
		return memberService.activeAccount(accountRequest);
	}
	
	@PostMapping(value = "log_in")
	public MemberResponse logIn(@RequestBody MemberRequest accountRequest) {		
		return memberService.logIn(accountRequest);
	}
	
	@PostMapping(value = "log_out")
	public MemberResponse logOut(@RequestBody MemberRequest accountRequest) {
		accountRequest.getHttpSession().removeAttribute("memberId");
		accountRequest.getHttpSession().removeAttribute("pwd");
		return new MemberResponse(MemberRtnCode.LOG_OUT_SUCCESS.getMessage());
	}
	
	@PostMapping(value = "update_pwd")
	public MemberResponse updatePwd(@RequestBody MemberRequest updateRequest) {
		return memberService.updatePwd(updateRequest);
	}
	
	@PostMapping(value = "update_member_name")
	public MemberResponse updateMemberName(@RequestBody MemberRequest updateRequest) {
		return memberService.updateMemberName(updateRequest);
	}
	
	@PostMapping(value = "update_phone")
	public MemberResponse updatePhone(@RequestBody MemberRequest updateRequest) {
		return memberService.updatePhone(updateRequest);
	}
	
	@PostMapping(value = "update_birthday")
	public MemberResponse updateBirthday(@RequestBody MemberRequest updateRequest) {
		return memberService.updateBirthday(updateRequest);
	}
	

}
