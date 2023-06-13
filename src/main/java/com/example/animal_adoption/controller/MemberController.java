package com.example.animal_adoption.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
		
		// 產生驗證碼
		double random = Math.random()*10000;
		int verifyCode = (int)Math.round(random);

		// 設定驗證碼到session
		httpSession.setAttribute("verifyCode", verifyCode);
		
		res.setSessionId(httpSession.getId());
		res.setVerifyCode(verifyCode);
		
		return res;
	}
	
	@PostMapping(value = "log_in_verify")
	public MemberResponse logInVerify(@RequestBody MemberRequest accountRequest, HttpSession httpSession) {		
		MemberResponse res = memberService.logInVerify(accountRequest);
		if (!res.getMessage().equals(MemberRtnCode.LOG_IN_VERIFY_SUCCESS.getMessage())) {
			return res;
		}
		
		Integer verifyCode = (Integer) httpSession.getAttribute("verifyCode"); 		
	    // 判斷驗證碼是否錯誤
		if (accountRequest.getVerifyCode() != verifyCode) {
			return new MemberResponse(MemberRtnCode.VERIFY_CODE_ERROR.getMessage());
		}
		
		// 設定帳號密碼到session
		httpSession.setAttribute(SessionCode.MEMBER_ID.getCode(), accountRequest.getMemberId());
		httpSession.setAttribute(SessionCode.MEMBER_PWD.getCode(), accountRequest.getPwd());
		httpSession.setMaxInactiveInterval(1800);
		
		return res;
	}
	
	@PostMapping(value = "log_out")
	public MemberResponse logOut(@RequestBody MemberRequest accountRequest, HttpSession httpSession) {
		httpSession.removeAttribute(SessionCode.MEMBER_ID.getCode());
		httpSession.removeAttribute(SessionCode.MEMBER_PWD.getCode());
		return new MemberResponse(MemberRtnCode.LOG_OUT_SUCCESS.getMessage());
	}
	
	@PostMapping(value = "update_pwd")
	public MemberResponse updatePwd(@RequestBody MemberRequest updateRequest, HttpSession httpSession) {
		// session判斷是否有登入
		String sessionMemberId = (String) httpSession.getAttribute(SessionCode.MEMBER_ID.getCode());
		String sessionPwd = (String) httpSession.getAttribute(SessionCode.MEMBER_PWD.getCode());
		
	    if (!StringUtils.hasText(sessionMemberId) || !StringUtils.hasText(sessionPwd)) {
	      return new MemberResponse(MemberRtnCode.NOT_LOG_IN.getMessage());
	    }
	    
		return memberService.updatePwd(updateRequest);
	}
	
	@PostMapping(value = "update_member_name")
	public MemberResponse updateMemberName(@RequestBody MemberRequest updateRequest, HttpSession httpSession) {
		// session判斷是否有登入
		String sessionMemberId = (String) httpSession.getAttribute(SessionCode.MEMBER_ID.getCode());
		String sessionPwd = (String) httpSession.getAttribute(SessionCode.MEMBER_PWD.getCode());
		
	    if (!StringUtils.hasText(sessionMemberId) || !StringUtils.hasText(sessionPwd)) {
	      return new MemberResponse(MemberRtnCode.NOT_LOG_IN.getMessage());
	    }
	    
		return memberService.updateMemberName(updateRequest);
	}
	
	@PostMapping(value = "update_phone")
	public MemberResponse updatePhone(@RequestBody MemberRequest updateRequest, HttpSession httpSession) {
		// session判斷是否有登入
		String sessionMemberId = (String) httpSession.getAttribute(SessionCode.MEMBER_ID.getCode());
		String sessionPwd = (String) httpSession.getAttribute(SessionCode.MEMBER_PWD.getCode());
		
	    if (!StringUtils.hasText(sessionMemberId) || !StringUtils.hasText(sessionPwd)) {
	      return new MemberResponse(MemberRtnCode.NOT_LOG_IN.getMessage());
	    }
		
		return memberService.updatePhone(updateRequest);
	}
	
	@PostMapping(value = "update_birthday")
	public MemberResponse updateBirthday(@RequestBody MemberRequest updateRequest, HttpSession httpSession) {
		// session判斷是否有登入
		String sessionMemberId = (String) httpSession.getAttribute(SessionCode.MEMBER_ID.getCode());
		String sessionPwd = (String) httpSession.getAttribute(SessionCode.MEMBER_PWD.getCode());
		
	    if (!StringUtils.hasText(sessionMemberId) || !StringUtils.hasText(sessionPwd)) {
	      return new MemberResponse(MemberRtnCode.NOT_LOG_IN.getMessage());
	    }
		
		return memberService.updateBirthday(updateRequest);
	}
	
	@PostMapping(value= "update_session_interval")
	public void updateSessionInterval(HttpSession httpSession) {
		// 設定session時間: 30分鐘 
		httpSession.setMaxInactiveInterval(1800);  // (單位是秒)
	}
	

}