package com.example.animal_adoption.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.animal_adoption.constants.MemberRtnCode;
import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.entity.Member;
import com.example.animal_adoption.repository.MemberDao;
import com.example.animal_adoption.service.ifs.MemberService;
import com.example.animal_adoption.vo.MemberResponse;
import com.example.animal_adoption.vo.AnimalAdoptionResponse;
import com.example.animal_adoption.vo.MemberRequest;

@Service
public class MemberImpl implements MemberService{

	@Autowired
	HttpSession session;
	
	@Autowired
	MemberDao memberDao;

	@Override
	//會員註冊
	public MemberResponse signUp(MemberRequest signUpRequest) {		
		// 取出輸入的會員資訊
		String memberId = signUpRequest.getMemberId();
		String pwd = signUpRequest.getPwd();
		String memberName = signUpRequest.getMemberName();
		String phone = signUpRequest.getPhone();
		String birth = signUpRequest.getBirth();
		
		// 判斷資料是否為空
	    if (!StringUtils.hasText(memberId)
	    		|| !StringUtils.hasText(pwd)
	    		|| !StringUtils.hasText(memberName)
	    		|| !StringUtils.hasText(phone)
	    		|| !StringUtils.hasText(birth)) {
	    	return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
	    
		// 判斷會員是否已經存在
		Optional<Member> op = memberDao.findById(memberId);
		if (op.isPresent()) {
			return new MemberResponse(MemberRtnCode.MEMBER_IS_PRESENT.getMessage());
		}
	    
	    // 確認格式: 身分證、密碼、手機
	    String idPattern = "^[A-Z][1-2]\\d{8}$";
	    String pwdPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d\\S]{8,12}$";
	    String phonePattern = "^09\\d{8}$";
	    
	    if (!memberId.matches(idPattern)
	    		|| !pwd.matches(pwdPattern)
	    		|| !phone.matches(phonePattern)) {
	    	return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
	    
	    // 新增會員資料
		Member addMember = new Member();
	    // 字串轉LocalDate
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDateBirth = LocalDate.parse(birth, formatter);
		
		addMember.setMemberId(memberId);
		addMember.setPwd(pwd);
		addMember.setMemberName(memberName);
		addMember.setPhone(phone);
		addMember.setBirth(localDateBirth);
		addMember.setFav(null);
		addMember.setCarId(null);
		addMember.setOrderId(null);
		addMember.setAdministrator(false);
	   
		memberDao.save(addMember);
		
		return new MemberResponse(addMember, MemberRtnCode.SIGN_UP_SUCCESS.getMessage());
	}

	@Override
	//帳號生效
	public MemberResponse activeAccount(MemberRequest accountRequest) {
		// 取出輸入的會員資訊
		String memberId = accountRequest.getMemberId();
		String pwd = accountRequest.getPwd();
		
		// 判斷資料是否為空
	    if (!StringUtils.hasText(memberId)
	    		|| !StringUtils.hasText(pwd)) {
	    	return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
		
		// 判斷會員是否已經存在
		Member member = memberDao.findByMemberIdAndPwd(memberId, pwd);
		if (member == null) {
			return new MemberResponse(MemberRtnCode.MEMBER_NOT_PRESENT.getMessage());
		}
		
		// 判斷會員是否已經生效
		if (member.isActive() == true) {
			return new MemberResponse(MemberRtnCode.MEMBER_ALREADY_ACTIVE.getMessage());
		}
		
		// 會員生效欄位設定true
		member.setActive(true);
		memberDao.save(member);
		
		return new MemberResponse(member, MemberRtnCode.ACTTIVE_MEMBER_SUCCESS.getMessage());
		
	}

	@Override
	//會員登入
	public MemberResponse logIn(MemberRequest accountRequest) {
	    // 取得客戶端Session
	    HttpSession clientSession = accountRequest.getHttpSession();
	    // 驗證客戶端Id與伺服器端Id，判斷是否有登入
	    String serviceSession = (String) session.getAttribute(clientSession.getId());
	    if (!StringUtils.hasText(serviceSession)) {
	      return new MemberResponse(MemberRtnCode.NOT_LOG_IN.getMessage());
	    }
	    
	    
		HttpSession httpSession = accountRequest.getHttpSession();
		String sessionMemberId = (String) httpSession.getAttribute("memberId");  
		String sessionPwd = (String) httpSession.getAttribute("pwd");
		
		// verifycode不確定寫哪
//		Integer sessionVerifyCode = (Integer) httpSession.getAttribute("verifyCode"); 
		
		//request是要帶入輸入的verifyCode
//		return registerService.getRegTime2(request, sessionAccount, sessionPwd, sessionVerifyCode);
	    
		
		if (!StringUtils.hasText(sessionMemberId) || !StringUtils.hasText(sessionPwd)) {
			return new MemberResponse(MemberRtnCode.NOT_LOG_IN.getMessage());
		}
//		if (sessionVerifyCode == null || sessionVerifyCode != request.getVerifyCode()) {
//			return new MemberResponse("Verify code incorrect!");
//		}
		Member res = memberDao.findByMemberIdAndPwdAndIsActive(sessionMemberId, sessionPwd, true);
		if (res == null) {
			return new MemberResponse(MemberRtnCode.MEMBER_NOT_PRESENT_OR_PWD_ERROR_OR_NOT_ACTIVE.getMessage());
		}
		
		
		// 取出輸入的會員資訊
//		String memberId = accountRequest.getMemberId();
//		String pwd = accountRequest.getPwd();
		
		// 判斷資料是否為空
//	    if (!StringUtils.hasText(memberId)
//	    		|| !StringUtils.hasText(pwd)) {
//	    	return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
//	    }
		
		// 判斷會員是否已經存在
//		Member member = memberDao.findByMemberIdAndPwdAndIsActive(memberId, pwd, true);
//		if (member == null) {
//			return new MemberResponse(MemberRtnCode.MEMBER_NOT_PRESENT_OR_PWD_ERROR_OR_NOT_ACTIVE.getMessage());
//		}
		
//		// 判斷會員是否已經生效
//		if (member.isActive() == false) {
//			return new MemberResponse(MemberRtnCode.MEMBER_NOT_ACTIVE.getMessage());
//		}
		
		return new MemberResponse(MemberRtnCode.LOG_IN_SUCCESS.getMessage());
	}

	@Override
	//修改會員密碼
	public MemberResponse updatePwd(MemberRequest updateRequest) {
//	    // 取得客戶端Session
//	    HttpSession clientSession = updateRequest.getHttpSession();
//	    // 驗證客戶端Id與伺服器端Id，判斷是否有登入
//	    String serviceSession = (String) session.getAttribute(clientSession.getId());
//	    if (!StringUtils.hasText(serviceSession)) {
//	      return new MemberResponse(MemberRtnCode.NOT_LOG_IN.getMessage());
//	    }
		
		// 取出輸入的會員資訊
		String memberId = updateRequest.getMemberId();
		String pwd = updateRequest.getPwd();
		
		// 判斷資料是否為空
	    if (!StringUtils.hasText(memberId)
	    		|| !StringUtils.hasText(pwd)) {
	    	return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
	    
	    // 判斷資料是否與原本相同
	    Optional<Member> op = memberDao.findById(memberId);
	    String pwdOfDao = op.get().getPwd();
	    if (pwd.equals(pwdOfDao)) {
	    	return new MemberResponse(MemberRtnCode.SAME_PWD.getMessage());
	    }
	    
	    // 確認格式: 密碼
	    String pwdPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d\\S]{8,12}$";
	    
	    if (!pwd.matches(pwdPattern)) {
	    	return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
	    
	    // 更新密碼
	    op.get().setPwd(pwd);
 		
 		memberDao.save(op.get());
	    
 		return new MemberResponse(MemberRtnCode.UPDATE_MEMBER_INFO_SUCCESS.getMessage());
	}

	@Override
	//修改會員姓名
	public MemberResponse updateMemberName(MemberRequest updateRequest) {
		// 取出輸入的會員資訊
		String memberId = updateRequest.getMemberId();
		String memberName = updateRequest.getMemberName();
		
		// 判斷資料是否為空
	    if (!StringUtils.hasText(memberId)
	    		|| !StringUtils.hasText(memberName)) {
	    	return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
	    
	    // 判斷資料是否與原本相同
	    Optional<Member> op = memberDao.findById(memberId);
	    String memberNameOfDao = op.get().getMemberName();
	    if (memberName.equals(memberNameOfDao)) {
	    	return new MemberResponse(MemberRtnCode.SAME_MEMBER_NAME.getMessage());
	    }
	    
	    // 更新姓名
	    op.get().setMemberName(memberName);
 		
 		memberDao.save(op.get());
	    
 		return new MemberResponse(MemberRtnCode.UPDATE_MEMBER_INFO_SUCCESS.getMessage());
	}

	@Override
	//修改會員手機
	public MemberResponse updatePhone(MemberRequest updateRequest) {
		// 取出輸入的會員資訊
		String memberId = updateRequest.getMemberId();
		String phone = updateRequest.getPhone();
		
		// 判斷資料是否為空
	    if (!StringUtils.hasText(memberId)
	    		|| !StringUtils.hasText(phone)) {
	    	return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
	    
	    
	    // 判斷資料是否與原本相同
	    Optional<Member> op = memberDao.findById(memberId);
	    String phoneOfDao = op.get().getPhone();
	    if (phone.equals(phoneOfDao)) {
	    	return new MemberResponse(MemberRtnCode.SAME_PHONE.getMessage());
	    }
	    
	    // 確認格式: 手機
	    String phonePattern = "^09\\d{8}$";
	    
	    if (!phone.matches(phonePattern)) {
	    	return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
	    
	    // 更新手機
	    op.get().setPhone(phone);
 		
 		memberDao.save(op.get());
	    
 		return new MemberResponse(MemberRtnCode.UPDATE_MEMBER_INFO_SUCCESS.getMessage());
	}

	@Override
	//修改會員生日
	public MemberResponse updateBirthday(MemberRequest updateRequest) {
		// 取出輸入的會員資訊
		String memberId = updateRequest.getMemberId();
		String birth = updateRequest.getBirth();
		
		// 判斷資料是否為空
	    if (!StringUtils.hasText(memberId)
	    		|| !StringUtils.hasText(birth)) {
	    	return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
	    
	    // 判斷資料是否與原本相同
	    Optional<Member> op = memberDao.findById(memberId);
	    LocalDate birthOfDao = op.get().getBirth();
	    // 字串轉LocalDate
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDateBirth = LocalDate.parse(birth, formatter);
	    if (localDateBirth.equals(birthOfDao)) {
	    	return new MemberResponse(MemberRtnCode.SAME_BIRTHDAY.getMessage());
	    }
	    
	    // 更新生日
	    op.get().setBirth(localDateBirth);;
 		
 		memberDao.save(op.get());
	    
 		return new MemberResponse(MemberRtnCode.UPDATE_MEMBER_INFO_SUCCESS.getMessage());
	}
		
}
