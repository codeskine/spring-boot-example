package org.codeskine.tutorial.springboot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@Document
public class User implements Persistable<String> {

  @Id
  private String id;
  private String firstName;
  private String lastName;

  @Indexed
  private String email;

  @DocumentReference(lazy = true)
  private Company company;

  public static User of(String firstName, String lastName, String email) {
    var user = new User();
    user.firstName = firstName;
    user.lastName = lastName;
    user.email = email;

    return user;
  }

  @Override
  @Transient
  public boolean isNew() {
    return id == null;
  }
}
