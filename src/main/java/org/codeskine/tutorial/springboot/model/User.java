package org.codeskine.tutorial.springboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class User {

	@Id private String id;
	private String firstName;
	private String lastName;
	
	@Indexed
	private String email;
	
	 public User(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	
	
}
