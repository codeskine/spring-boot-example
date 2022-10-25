package org.codeskine.tutorial.springboot;

import org.codeskine.tutorial.springboot.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class InitDatabase {

  @Bean
  CommandLineRunner init(MongoOperations operations) {
    return args -> {
      operations.dropCollection(User.class);
      operations.insert(new User("Stefano", "Veloccia", "s.veloccia@innen.it"));
      operations.insert(new User("Alexander", "De Carlo", "a.decarlo@innen.it"));

      operations.findAll(User.class).forEach(image -> {
        System.out.println(image.toString());
      });
    };
  }
}