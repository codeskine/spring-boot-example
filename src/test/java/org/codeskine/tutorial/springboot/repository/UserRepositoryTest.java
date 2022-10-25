package org.codeskine.tutorial.springboot.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.codeskine.tutorial.springboot.configuration.ApplicationTestConfiguration;
import org.codeskine.tutorial.springboot.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@Import(ApplicationTestConfiguration.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void findByEmail() {
    User user = userRepository.findByEmail("s.veloccia@innen.it");

    assertNotNull(user);
    assertEquals("s.veloccia@innen.it", user.getEmail());
    assertEquals("Stefano", user.getFirstName());

  }
}