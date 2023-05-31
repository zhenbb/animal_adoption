package com.example.animal_adoption.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.animal_adoption.service.ifs.MemberService;
import com.example.animal_adoption.vo.MemberAccountRequest;
import com.example.animal_adoption.vo.MemberResponse;
import com.example.animal_adoption.vo.MemberSignUpRequest;
import com.example.animal_adoption.vo.MemberUpdateRequest;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping(value = "sign_up")
	public MemberResponse signUp(@RequestBody MemberSignUpRequest signUpRequest) {
	  return memberService.signUp(signUpRequest);
	}
	
	@PostMapping(value = "active_account")
	public MemberResponse activeAccount(@RequestBody MemberAccountRequest accountRequest) {
	  return memberService.activeAccount(accountRequest.getMemberId(), accountRequest.getPwd());
	}
	
	@PostMapping(value = "log_in")
	public MemberResponse logIn(@RequestBody MemberAccountRequest accountRequest) {
	  return memberService.logIn(accountRequest.getMemberId(), accountRequest.getPwd());
	}
	
	@PostMapping(value = "log_out")
	public MemberResponse logOut(@RequestBody MemberAccountRequest accountRequest) {
		accountRequest.getHttpSession().removeAttribute("memberId");
		accountRequest.getHttpSession().removeAttribute("pwd");
		return new MemberResponse("登出成功");
	}
	
	@PostMapping(value = "update_pwd")
	public MemberResponse updatePwd(@RequestBody MemberUpdateRequest updateRequest) {
	  return memberService.updatePwd(updateRequest.getMemberId(), updateRequest.getPwd());
	}
	
	@PostMapping(value = "update_member_name")
	public MemberResponse updateMemberName(@RequestBody MemberUpdateRequest updateRequest) {
	  return memberService.updateMemberName(updateRequest.getMemberId(), updateRequest.getMemberName());
	}
	
	@PostMapping(value = "update_phone")
	public MemberResponse updatePhone(@RequestBody MemberUpdateRequest updateRequest) {
	  return memberService.updatePhone(updateRequest.getMemberId(), updateRequest.getPhone());
	}
	
	@PostMapping(value = "update_birthday")
	public MemberResponse updateBirthday(@RequestBody MemberUpdateRequest updateRequest) {
	  return memberService.updateBirthday(updateRequest.getMemberId(), updateRequest.getBirth());
	}
	

}
