package com.example.animal_adoption.repository;

import com.example.animal_adoption.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Member, String> {
}
