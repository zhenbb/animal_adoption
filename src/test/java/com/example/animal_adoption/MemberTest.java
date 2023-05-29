package com.example.animal_adoption;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.animal_adoption.constants.MemberRtnCode;
import com.example.animal_adoption.entity.Member;
import com.example.animal_adoption.repository.MemberDao;
import com.example.animal_adoption.service.ifs.MemberService;
import com.example.animal_adoption.vo.MemberResponse;
import com.example.animal_adoption.vo.MemberSignUpRequest;

@SpringBootTest(classes = AnimalAdoptionApplication.class)
public class MemberTest {

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private MemberService memberService;
	
	@Test
	//會員註冊
	public void signUpTest() {
		
	}
	
	@Test
	// 判斷會員是否已經存在
	public void signUpPresentTest() {
		// 建立假資料
        Member member = new Member();
        member.setMemberId("E123456789");
        member.setPwd("aaa45678");
        member.setMemberName("王一");
        member.setPhone("0987654321");
        LocalDate birthday = LocalDate.of(2000, 10, 10);
        member.setBirth(birthday);
        
        // 建立假Request
        MemberSignUpRequest memberRequest = new MemberSignUpRequest();
        memberRequest.setMember(member);

        // 執行測試
//        MemberResponse actualResponse = memberService.signUp(memberRequest);
//        MemberResponse actualResponse = memberService.signUp(member);
        
        // 驗證結果
        MemberResponse expectedResponse = new MemberResponse(member, MemberRtnCode.SIGN_UP_SUCCESS.getMessage());

//        Assertions.assertEquals(expectedResponse, actualResponse);
        System.out.println("fini");
	}
	
//	@Test
//	// 判斷會員是否已經存在
//	public void signupTest1() {
//		Member member = new Member();
//		member.setMemberId("E234567890");
//        member.setPwd("bbb45678");
//        member.setMemberName("謝二");
//        member.setPhone("0912345678");
//        LocalDate birthday = LocalDate.of(2001, 11, 11);
//        member.setBirth(birthday);
//		
//        MemberSignUpRequest signUpRequest = new MemberSignUpRequest();
//        signUpRequest.setMember(member);
//        
//		MemberResponse result = memberService.signUp(signUpRequest);
//		System.out.println(result.getMessage());
//	}
	
	@Test
	// 判斷會員是否已經存在
	public void signupTest2() {
		Member member = new Member();
//		member.setMemberId("E234567890");
//        member.setPwd("bbb45678");
//        member.setMemberName("謝二");
//        member.setPhone("0912345678");
//        LocalDate birthday = LocalDate.of(2001, 11, 11);
//        member.setBirth(birthday);
		MemberSignUpRequest signUpRequest = new MemberSignUpRequest();
		
        
		MemberResponse result = memberService.signUp(signUpRequest.getMember());
		System.out.println(result.getMessage());
		System.out.println("fini");
	}
	
	@Test
	public void activeAccountTest() {
		
	}
	
	@Test
	public void logInTest() {
		
	}
	
	@Test
	public void updatePwdTest() {
		
	}
	
	@Test
	public void updateMemberNameTest() {
		
	}
	
	@Test
	public void updatePhoneTest() {
		
	}
	
	@Test
	public void updateBirthdayTest() {
		
	}
	
	@Test
	public void logOutTest() {
		
	}
	
	
}
