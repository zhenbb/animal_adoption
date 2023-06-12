package com.example.animal_adoption.repository;

import com.example.animal_adoption.entity.Member;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao extends JpaRepository<Member, String> {
	
	public Member findByMemberIdAndPwd(String memberId, String pwd);
	
	public Member findByMemberIdAndPwdAndIsActive(String memberId, String pwd, boolean isActive);

	public Member findByMemberIdAndPwdAndMemberName(String memberId, String pwd, String memberName);
	
	public Member findByMemberIdAndPwdAndPhone(String memberId, String pwd, String phone);
	
	public Member findByMemberIdAndPwdAndBirth(String memberId, String pwd, LocalDate birth);
}