package com.example.animal_adoption.repository;

import com.example.animal_adoption.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao extends JpaRepository<Member, String> {
	
	public Member findByMemberIdAndPwd(String memberId, String pwd);
	
	public Member findByMemberIdAndPwdAndIsActive(String memberId, String pwd, boolean isActive);


}
