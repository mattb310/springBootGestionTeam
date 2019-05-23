package com.fr.adaming.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Entity 
public class Item {
	
	//@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Instrument instr;
	
	private Long qt;
	

}
