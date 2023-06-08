package com.example.animal_adoption.vo;

import com.example.animal_adoption.entity.Member;

public class GetCartProductRequest {

    public Member member;
    public String message;


    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}