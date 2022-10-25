package org.codeskine.tutorial.springboot;

import org.codeskine.tutorial.springboot.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class InitDatabase {

  private static final Logger logger = LoggerFactory.getLogger(InitDatabase.class);

  @Bean
  CommandLineRunner init(MongoOperations operations) {
    return args -> {
      operations.dropCollection(User.class);
      operations.insert(new User("Stefano", "Veloccia", "s.veloccia@innen.it"));
      operations.insert(new User("Alexander", "De Carlo", "a.decarlo@innen.it"));

      operations.findAll(User.class)
          .forEach(user -> logger.debug("Import user: {}", user));
    };
  }
}