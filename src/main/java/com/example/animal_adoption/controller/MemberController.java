package com.example.animal_adoption.controller;

import javax.servlet.http.HttpSession;

import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.repository.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.example.animal_adoption.constants.MemberRtnCode;
import com.example.animal_adoption.constants.SessionCode;
import com.example.animal_adoption.service.ifs.MemberService;
import com.example.animal_adoption.vo.MemberRequest;
import com.example.animal_adoption.vo.MemberResponse;

@CrossOrigin
@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;

  @Autowired
  private MemberDao memberDao;
	
	@PostMapping(value = "sign_up")
	public MemberResponse signUp(@RequestBody MemberRequest signUpRequest) {
		return memberService.signUp(signUpRequest);
	}
	
	@PostMapping(value = "active_account")
	public MemberResponse activeAccount(@RequestBody MemberRequest accountRequest) {
		return memberService.activeAccount(accountRequest);
	}
	
	@PostMapping(value = "log_in")
	public MemberResponse logIn(@RequestBody MemberRequest accountRequest, HttpSession httpSession) {		
		MemberResponse res = memberService.logIn(accountRequest);
		if (!res.getMessage().equals(MemberRtnCode.LOG_IN_SUCCESS.getMessage())) {
			return res;
		}
		
		// 設定帳號密碼到session
		httpSession.setAttribute(SessionCode.MEMBER_ID.getCode(), accountRequest.getMemberId());
		httpSession.setAttribute(SessionCode.MEMBER_PWD.getCode(), accountRequest.getPwd());
		httpSession.setMaxInactiveInterval(1800);
		
		res.setSessionId(httpSession.getId());
		
//		System.out.println(httpSession.getId());

		return res;
	}
	
//	@PostMapping(value = "log_out")
//	public MemberResponse logOut(@RequestBody MemberRequest accountRequest) {
//		httpSession.removeAttribute(SessionCode.MEMBER_ID.getCode());
//		httpSession.removeAttribute(SessionCode.MEMBER_PWD.getCode());
//		return new MemberResponse(MemberRtnCode.LOG_OUT_SUCCESS.getMessage());
//	}
	
	@PostMapping(value = "update_pwd")
	public MemberResponse updatePwd(@RequestBody MemberRequest updateRequest, HttpSession httpSession) {
		return memberService.updatePwd(updateRequest, httpSession);
	}
	
	@PostMapping(value = "update_member_name")
	public MemberResponse updateMemberName(@RequestBody MemberRequest updateRequest, HttpSession httpSession) {    
		return memberService.updateMemberName(updateRequest, httpSession);
	}
	
	@PostMapping(value = "update_phone")
	public MemberResponse updatePhone(@RequestBody MemberRequest updateRequest, HttpSession httpSession) {
		return memberService.updatePhone(updateRequest, httpSession);
	}
	
	@PostMapping(value = "update_birthday")
	public MemberResponse updateBirthday(@RequestBody MemberRequest updateRequest, HttpSession httpSession) {
		return memberService.updateBirthday(updateRequest, httpSession);
	}
	
	@PostMapping(value = "get_member_info")
	public MemberResponse getMemberInfo(@RequestBody MemberRequest updateRequest, HttpSession httpSession) {
		return memberService.getMemberInfo(updateRequest, httpSession);
	}

	@GetMapping(value= "update_session_interval")
	public void updateSessionInterval(HttpSession httpSession) {
		// 設定session時間: 30分鐘 
		httpSession.setMaxInactiveInterval(1800);  // (單位是秒)
	}

}