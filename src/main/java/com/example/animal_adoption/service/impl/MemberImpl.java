package com.example.animal_adoption.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.animal_adoption.repository.MemberDao;
import com.example.animal_adoption.service.ifs.MemberService;
import com.example.animal_adoption.vo.MemberAccountRequest;
import com.example.animal_adoption.vo.MemberResponse;
import com.example.animal_adoption.vo.MemberSignupRequest;
import com.example.animal_adoption.vo.MemberUpdateRequest;

@Service
public class MemberImpl implements MemberService{

	@Autowired
	HttpSession session;
	
	@Autowired
	MemberDao memberDao;

	@Override
	public MemberResponse signUp(MemberSignupRequest memberRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberResponse activeAccount(MemberAccountRequest memberRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberResponse logIn(MemberAccountRequest memberRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberResponse updatePwd(MemberUpdateRequest memberRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberResponse updateMemberName(MemberUpdateRequest memberRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberResponse updatePhone(MemberUpdateRequest memberRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberResponse updateBirthday(MemberUpdateRequest memberRequest) {
		// TODO Auto-generated method stub
		return null;
	}
		
}
