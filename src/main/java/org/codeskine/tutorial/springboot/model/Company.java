package org.codeskine.tutorial.springboot.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Company implements Persistable<String> {

  @Id
  private String id;

  private String name;

  @Override
  @Transient
  public boolean isNew() {
    return id == null;
  }
}
