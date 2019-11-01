package br.wesley.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CATS")
public class Cat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "AGE")
	private int age;

	@Column(name = "SEX", length = 6)
	private String sex;
	
	@Column(name = "NAME", length = 100)
	private String name;

}