package com.example.animal_adoption.entity;


@Entity
@Table(name =animal)
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "animal_id")
	private int animalId;
	
	

}
