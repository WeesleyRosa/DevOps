package br.wesley.http;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class DogPOJO {

	private long id;
	private int age;
	private String sex;
	private String name;

}