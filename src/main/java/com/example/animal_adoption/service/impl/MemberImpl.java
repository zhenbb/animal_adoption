package com.example.animal_adoption.service.impl;

import java.time.LocalDate;
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
	    Member member = signUpRequest.getMember();
		String memberId = member.getMemberId();
		String pwd = member.getPwd();
		String memberName = member.getMemberName();
		String phone = member.getPhone();
		LocalDate birth = member.getBirth();
		
		// 判斷會員是否已經存在
		Optional<Member> op = memberDao.findById(memberId);
		if (op.isPresent()) {
			return new MemberResponse(MemberRtnCode.MEMBER_IS_PRESENT.getMessage());
		}
		
		// 判斷資料是否為空
	    if (member == null
	    		|| !StringUtils.hasText(memberId)
	    		|| !StringUtils.hasText(pwd)
	    		|| !StringUtils.hasText(memberName)
	    		|| !StringUtils.hasText(phone)
			  	|| birth == null) {
	    	return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
	    
	    // 設定我的最愛、購物車、購買清單、管理者為預設值
	    member.setFav(null);
	    member.setCarId(null);
	    member.setOrderId(null);
	    member.setAdministrator(false);
	    
	    // 確認格式: 身分證、密碼、手機
	    String idPattern = "^[A-Z][1-2]\\d{8}$";
	    String pwdPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d\\S]{8,12}$";
	    String phonePattern = "^09\\d{8}$";
	    
	    if (!memberId.matches(idPattern)
	    		|| !pwd.matches(pwdPattern)
	    		|| !phone.matches(phonePattern)) {
	    	return new MemberResponse(MemberRtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
	   
		memberDao.save(member);
		
		return new MemberResponse(member, MemberRtnCode.SIGN_UP_SUCCESS.getMessage());
	}

	@Override
	//帳號生效
	public MemberResponse activeAccount(MemberAccountRequest memberRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//會員登入
	public MemberResponse logIn(MemberAccountRequest memberRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//修改會員密碼
	public MemberResponse updatePwd(MemberUpdateRequest memberRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//修改會員姓名
	public MemberResponse updateMemberName(MemberUpdateRequest memberRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//修改會員手機
	public MemberResponse updatePhone(MemberUpdateRequest memberRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//修改會員生日
	public MemberResponse updateBirthday(MemberUpdateRequest memberRequest) {
		// TODO Auto-generated method stub
		return null;
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
