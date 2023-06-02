package com.example.animal_adoption.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member {

  @Id
  @Column(name = "member_id")
  private String memberId;
  
  @Column(name = "password")
  private String pwd;

  @Column(name = "member_name")
  private String memberName;

  @Column(name = "phone")
  private String phone;

  @Column(name = "birthday")
  private LocalDate birth;

  @Column(name = "fav")
  private String fav;

  @Column(name = "car_id")
  private Integer carId;

  @Column(name = "order_id")
  private String orderId;

  @Column(name = "is_administrator")
  private boolean isAdministrator;
  
  
  @Column(name = "is_active")
  private boolean isActive;

  public Member() {
  }

  public String getMemberId() {
    return memberId;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public String getPwd() {
	return pwd;
  }

  public void setPwd(String pwd) {
	this.pwd = pwd;
  }

  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public LocalDate getBirth() {
    return birth;
  }

  public void setBirth(LocalDate birth) {
    this.birth = birth;
  }

  public String getFav() {
    return fav;
  }

  public void setFav(String fav) {
    this.fav = fav;
  }

  public Integer getCarId() {
    return carId;
  }

  public void setCarId(Integer carId) {
    this.carId = carId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public boolean isAdministrator() {
    return isAdministrator;
  }

  public void setAdministrator(boolean administrator) {
    isAdministrator = administrator;
  }

  public boolean isActive() {
	  return isActive;
  }

  public void setActive(boolean isActive) {
	  this.isActive = isActive;
  }
  
}
