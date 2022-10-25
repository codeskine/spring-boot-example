package org.codeskine.tutorial.springboot;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "my")
public class DemoMetadata {

	private String firstName;
	private String lastName;
	private String city;

}
