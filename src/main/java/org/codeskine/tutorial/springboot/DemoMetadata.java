package org.codeskine.tutorial.springboot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "my")
public class DemoMetadata {

  private String firstName;
  private String lastName;
  private String city;

}
