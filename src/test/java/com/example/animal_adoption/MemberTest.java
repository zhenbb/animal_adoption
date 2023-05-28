package com.example.animal_adoption;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.animal_adoption.repository.MemberDao;
import com.example.animal_adoption.service.ifs.MemberService;

@SpringBootTest(classes = AnimalAdoptionApplication.class)
public class MemberTest {

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private MemberService memberService;
	
	@Test
	public void signUpTest() {
		
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
