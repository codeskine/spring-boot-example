package org.codeskine.tutorial.springboot.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
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
    Optional<User> optional = userRepository.findByEmail("s.veloccia@innen.it");

    assertThat(optional).isPresent();
    assertEquals("s.veloccia@innen.it", optional.get().getEmail());
    assertEquals("Stefano", optional.get().getFirstName());

  }
}