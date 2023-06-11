package com.example.animal_adoption.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "animal")
public class Animal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "animal_id")
  private int animalId;

  @Column(name = "animal_name")
  private String animalName;

  @Column(name = "sex")
  private boolean sex;

  @Column(name = "species")
  private boolean species = true;

  @Column(name = "type")
  private String type;

  @Column(name = "reg_date")
  private LocalDate regDate = LocalDate.now();

  @Column(name = "reg_city")
  private String regCity;

  @Column(name = "member_id")
  private String memberId;

  @Column(name = "adopt_date")
  private LocalDate adoptDate;


  public int getAnimalId() {
    return animalId;
  }

  public void setAnimalId(int animalId) {
    this.animalId = animalId;
  }

  public String getAnimalName() {
    return animalName;
  }

  public void setAnimalName(String animalName) {
    this.animalName = animalName;
  }

  public boolean isSex() {
    return sex;
  }

  public void setSex(boolean sex) {
    this.sex = sex;
  }

  public boolean isSpecies() {
    return species;
  }

  public void setSpecies(boolean species) {
    this.species = species;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public LocalDate getRegDate() {
    return regDate;
  }

  public void setRegDate(LocalDate regDate) {
    this.regDate = regDate;
  }

  public String getRegCity() {
    return regCity;
  }

  public void setRegCity(String regCity) {
    this.regCity = regCity;
  }

  public String getMemberId() {
    return memberId;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public LocalDate getAdoptDate() {
    return adoptDate;
  }

  public void setAdoptDate(LocalDate adoptDate) {
    this.adoptDate = adoptDate;
  }

  @Override
  public String toString() {
    return "Animal:{\nanimalId: " + animalId + "\nanimalName: " + animalName + "\nsex: " + sex + "\nspecies: " + species + "\ntype: " + "\nregDate: " + regDate + "\nregCity: " + regCity + "}";
  }
}