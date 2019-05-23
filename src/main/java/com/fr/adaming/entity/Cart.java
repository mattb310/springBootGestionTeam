package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Cart {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//@ManyToMany
	private User customer;
	//@OneToMany
	private List<Item> listItems;
	
}
