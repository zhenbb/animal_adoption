package com.example.animal_adoption.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.animal_adoption.constants.MemberRtnCode;
import com.example.animal_adoption.entity.Member;
import com.example.animal_adoption.repository.MemberDao;
import com.example.animal_adoption.service.ifs.MemberService;
import com.example.animal_adoption.vo.MemberAccountRequest;
import com.example.animal_adoption.vo.MemberResponse;
import com.example.animal_adoption.vo.MemberSignUpRequest;
import com.example.animal_adoption.vo.MemberUpdateRequest;

@Service
public class MemberImpl implements MemberService{

	@Autowired
	HttpSession session;
	
	@Autowired
	MemberDao memberDao;

	@Override
	//會員註冊
	public MemberResponse signUp(MemberSignUpRequest signUpRequest) {		
		// 取出輸入的會員資訊
		String memberId = signUpRequest.getMemberId();
		String pwd = signUpRequest.getPwd();
		String memberName = signUpRequest.getMemberName();
		String phone = signUpRequest.getPhone();
		String birth = signUpRequest.getBirth();
		
		// 判斷資料是否為空
	    if (!StringUtils.hasText(memberId)) {
	    	return new MemberResponse("memberId null");
	    }
	    if (!StringUtils.hasText(pwd)) {
	    	return new MemberResponse("pwd null");
	    }
	    if (!StringUtils.hasText(memberName)) {
	    	return new MemberResponse("memberName null");
	    }
	    if (!StringUtils.hasText(phone)) {
	    	return new MemberResponse("phone null");
	    }
	    if (!StringUtils.hasText(birth)) {
	    	return new MemberResponse("birth null");
	    }
	    
	    
//	    if (!StringUtils.hasText(memberId)
//	    		|| !StringUtils.hasText(pwd)
//	    		|| !StringUtils.hasText(memberName)
//	    		|| !StringUtils.hasText(phone)
//	    		|| !StringUtils.hasText(birth)) {
//	    	return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
//	    }
	    
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
	public MemberResponse activeAccount(String memberId, String pwd) {
		// 判斷資料是否為空
	    if (!StringUtils.hasText(memberId)
	    		|| !StringUtils.hasText(pwd)) {
	    	return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
	    
		// 判斷會員是否已經存在
		Optional<Member> op = memberDao.findById(memberId);
		if (!op.isPresent()) {
			return new MemberResponse(MemberRtnCode.MEMBER_NOT_PRESENT.getMessage());
		}
		
		// 判斷會員是否已經生效
		Member member = op.get();
		if (member.isActive() == true) {
			return new MemberResponse(MemberRtnCode.MEMBER_ALREADY_ACTIVE.getMessage());
		}
		
		// 生效的會員設定true
		Member newMember = new Member();
		newMember.setActive(true);
		
		memberDao.save(newMember);
		
		return new MemberResponse(newMember, MemberRtnCode.ACTTIVE_MEMBER_SUCCESS.getMessage());
		
	}

	@Override
	//會員登入
	public MemberResponse logIn(String memberId, String pwd) {
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
		if (member.isActive() == false) {
			return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
		}
		
		return new MemberResponse(MemberRtnCode.LOG_IN_SUCCESS.getMessage());
	}

	@Override
	//修改會員密碼
	public MemberResponse updatePwd(String memberId, String pwd) {
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
	    
	    // 更新密碼
	    op.get().setPwd(pwd);
 		
 		memberDao.save(op.get());
	    
 		return new MemberResponse(MemberRtnCode.UPDATE_MEMBER_INFO_SUCCESS.getMessage());
	}

	@Override
	//修改會員姓名
	public MemberResponse updateMemberName(String memberId, String memberName) {
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
	public MemberResponse updatePhone(String memberId, String phone) {
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
	    
	    // 更新手機
	    op.get().setPhone(phone);
 		
 		memberDao.save(op.get());
	    
 		return new MemberResponse(MemberRtnCode.UPDATE_MEMBER_INFO_SUCCESS.getMessage());
	}

	@Override
	//修改會員生日
	public MemberResponse updateBirthday(String memberId, String birth) {
		// 判斷資料是否為空
	    if (!StringUtils.hasText(memberId)
	    		|| birth == null) {
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
	
	
	
	
	
	
    // 資料不符返回true
//    private boolean memberCheck(Member member) {
//    	return member == null
//		  || !StringUtils.hasText(member.getMemberId())
//		  || !StringUtils.hasText(member.getPwd())
//		  || !StringUtils.hasText(member.getMemberName())
//		  || !StringUtils.hasText(member.getPhone())
//		  || member.getBirth() == null;			
//    }
		
}
