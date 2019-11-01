package br.wesley.http;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@XmlRootElement
public class CatPOJO {

	private long id;
	private int age;
	private String sex;
	private String name;

}